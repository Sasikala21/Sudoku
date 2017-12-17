import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;

public class InputPad extends JDialog{
	private JButton[] btns=new JButton[9];
	GameCell parent;
	public InputPad(){
		this.setUndecorated(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLayout(new GridLayout(3,3));
		for(int i=0;i<9;i++){
			final int num=i+1;
			btns[i]=new JButton();
			btns[i].setBackground(Color.ORANGE);
			btns[i].setFocusPainted(false);
			btns[i].setText(num+"");
			btns[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(e.getButton()==MouseEvent.BUTTON1){
						super.mouseClicked(e);
						parent.setByInput(num);
						//InputPad.this.dispose();
					}
					InputPad.this.setVisible(false);
				}
			});
			add(btns[i]);
		}
	}
	public void setLocation(GameCell parent,int x,int y,int width,int height){
		this.parent=parent;
		setBounds(x,y,width,height);	//设置位置
		setLocationRelativeTo(parent);
		for(int i=0;i<9;i++){	//按钮设置
			btns[i].setFont(new Font("Courier New",Font.PLAIN,(height+width)/15));
		}
		//this.setModal(true);
		setVisible(true);
	}
}
