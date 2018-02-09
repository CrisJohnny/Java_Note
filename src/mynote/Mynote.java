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
	
	//������Ҫ�����
	JTextArea jta=null;
	//������
	JScrollPane jsl=null;
	//����˵���
	JMenuBar jmb=null;
	//��һJMenu
	JMenu jm1=null;
	//����JMenuItem
	JMenuItem jmi1=null;
	JMenuItem jmi2=null;
	
	
	public static void main(String[] args)
	{
		Mynote mynote=new Mynote();
		
	}
	

	//���캯��
	public Mynote()
	{
		
		//����jta
		jta=new JTextArea();
		
		jsl=new JScrollPane(jta);
		
		jmb=new JMenuBar();
		
		jm1=new JMenu("��(O)");
		//�������Ƿ�
		jm1.setMnemonic('F');
		jmi1=new JMenuItem("��");
		
		//ע�����
		jmi1.addActionListener(this);
		jmi1.setActionCommand("open");
		
		jmi2=new JMenuItem("����");
		jmi2.addActionListener(this);
		jmi2.setActionCommand("save");
		//����
		this.setJMenuBar(jmb);
		
		//��jm1����jmb
		jmb.add(jm1);
		
		
		
		//��item����jm1
		jm1.add(jmi1);
		jm1.add(jmi2);
		//���뵽JFrame
		this.add(jsl);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400,300);
		this.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent a) {
		// TODO �Զ����ɵķ������
		//�ж����ĸ��˵���ѡ��
		if(a.getActionCommand().equals("open"))
		{
			
			JFileChooser jfc1=new JFileChooser();//ѡ��Ի���
			jfc1.showOpenDialog(null);//Ĭ����ʽ
			jfc1.setVisible(true);//��ʾ
			String filename=jfc1.getSelectedFile().getAbsolutePath();//�ļ�ȫ·��
			
			FileReader f=null;//�ı���

			try {
				f = new FileReader(filename);
				char c[] = new char[1024];
				String allCon="";
				while((f.read(c))!=-1)
				{
					allCon=c.toString()+"\r\n";
				}
				//���õ�jta����
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
			//����Ի���
			JFileChooser jfc=new JFileChooser();
			jfc.showSaveDialog(null);
			jfc.setVisible(true);//��ʾ
			String file=jfc.getSelectedFile().getAbsolutePath();//�õ��ļ�����·��

			FileWriter fw=null;//�ı���
			
			try {
				fw = new FileWriter(file);
				fw.write(jta.getText());//���õ����ı�д��fw
				
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
