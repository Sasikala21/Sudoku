import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;


public class MainFrame extends JFrame{
	public MainFrame(String title){
		super(title);
		init();
	}
	private void init(){
		Dimension wndSize=getToolkit().getScreenSize();
		setBounds(wndSize.width/4,wndSize.height/8,wndSize.width/2,wndSize.height*7/8);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		initMenu();
		initGamePanel();
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				System.out.println(e.getSource());
				if(e.getSource().getClass()==JMenuItem.class){
					System.out.println("�˵�");
				};
			}
		});
		setVisible(true);
	}
	private void initMenu(){
		JMenuBar menuBar=new JMenuBar();
		JMenu menuCtrl=new JMenu("����");
		menuCtrl.setFont(new Font("Sans-Serif",Font.PLAIN,16));
		MyMenuItem ctrlStart=new MyMenuItem("����Ϸ",Constant.Msg.New);
		MyMenuItem ctrlShow=new MyMenuItem("��ʾ�ⷨ",Constant.Msg.Show);
		MyMenuItem ctrlEnd=new MyMenuItem("����",Constant.Msg.Exit);
		menuCtrl.add(ctrlStart);
		menuCtrl.add(ctrlShow);
		menuCtrl.add(ctrlEnd);
		menuBar.add(menuCtrl);
		JMenu menuDfct=new JMenu("�Ѷ�");
		menuDfct.setFont(new  Font("Sans-Serif",0,16));
		MyMenuItem DfctEasy=new MyMenuItem("��",Constant.Msg.Easy);
		MyMenuItem DfctMedium=new MyMenuItem("�е�",Constant.Msg.Medium);
		MyMenuItem DfctHard=new MyMenuItem("����",Constant.Msg.Hard);
		MyMenuItem DfctExHd=new MyMenuItem("��̬",Constant.Msg.Evil);
		menuDfct.add(DfctEasy);
		menuDfct.add(DfctMedium);
		menuDfct.add(DfctHard);
		menuDfct.add(DfctExHd);
		menuBar.add(menuDfct);
		this.add(BorderLayout.NORTH,menuBar);
	}
	
	private void initGamePanel(){
		GamePanel gamePanel=new GamePanel();
		Constant.wnd=this;
		Constant.gamePanel=gamePanel;
		this.add(BorderLayout.CENTER,gamePanel);
	}
	public void success(){
		JDialog dlg=new JDialog(this, "Sudoku", true);
		dlg.setBounds(this.getWidth()/2-100, this.getHeight()/2-100, 200, 200);
		dlg.setLocationRelativeTo(this);
		dlg.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dlg.setLayout(new BorderLayout());
		JTextField txt=new JTextField("��Ϸ������");
		txt.setBounds(0,0,200,100);
		txt.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
		txt.setEditable(false);
		JButton btn=new JButton("ȷ��");
		btn.setBounds(60,180,40, 20);
		btn.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dlg.dispose();
			}
		});
		dlg.add(BorderLayout.CENTER,txt);
		dlg.add(BorderLayout.SOUTH,btn);
		dlg.setVisible(true);
	}
	public static void main(String[]args){
		MainFrame theApp=new MainFrame("Sudoku");
	}
}