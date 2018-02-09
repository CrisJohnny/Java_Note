package mynote;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Mynote extends JFrame implements ActionListener{
	
	//定义需要的组件
	JTextArea jta=null;
	//滚动条
	JScrollPane jsl=null;
	//定义菜单条
	JMenuBar jmb=null;
	//第一JMenu
	JMenu jm1=null;
	//定义JMenuItem
	JMenuItem jmi1=null;
	JMenuItem jmi2=null;
	
	
	public static void main(String[] args)
	{
		Mynote mynote=new Mynote();
		
	}
	

	//构造函数
	public Mynote()
	{
		
		//创建jta
		jta=new JTextArea();
		
		jsl=new JScrollPane(jta);
		
		jmb=new JMenuBar();
		
		jm1=new JMenu("打开(O)");
		//设置助记符
		jm1.setMnemonic('F');
		jmi1=new JMenuItem("打开");
		
		//注册监听
		jmi1.addActionListener(this);
		jmi1.setActionCommand("open");
		
		jmi2=new JMenuItem("保存");
		jmi2.addActionListener(this);
		jmi2.setActionCommand("save");
		//加入
		this.setJMenuBar(jmb);
		
		//把jm1放入jmb
		jmb.add(jm1);
		
		
		
		//把item放入jm1
		jm1.add(jmi1);
		jm1.add(jmi2);
		//放入到JFrame
		this.add(jsl);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400,300);
		this.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent a) {
		// TODO 自动生成的方法存根
		//判断是哪个菜单被选中
		if(a.getActionCommand().equals("open"))
		{
			
			JFileChooser jfc1=new JFileChooser();//选择对话框
			jfc1.showOpenDialog(null);//默认形式
			jfc1.setVisible(true);//显示
			String filename=jfc1.getSelectedFile().getAbsolutePath();//文件全路径
			
			FileReader f=null;//文本流

			try {
				f = new FileReader(filename);
				char c[] = new char[1024];
				String allCon="";
				while((f.read(c))!=-1)
				{
					allCon=c.toString()+"\r\n";
				}
				//放置到jta即可
				jta.setText(allCon);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally{
				
				try {
					f.close();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
			}
		}else if(a.getActionCommand().equals("save"))
		{
			//保存对话框
			JFileChooser jfc=new JFileChooser();
			jfc.showSaveDialog(null);
			jfc.setVisible(true);//显示
			String file=jfc.getSelectedFile().getAbsolutePath();//得到文件保存路径

			FileWriter fw=null;//文本流
			
			try {
				fw = new FileWriter(file);
				fw.write(jta.getText());//将得到的文本写入fw
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				try {
					fw.close();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
		

	}
}
