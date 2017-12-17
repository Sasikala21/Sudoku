import java.awt.Color;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;

class GameCell extends JButton{
	int x,y;
	private boolean m_changeable = false;

	public GameCell(int x, int y) {
		this.x=x;
		this.y=y;
		refresh(false);
		setFocusPainted(false);
		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				super.componentResized(e);
				GameCell.this.getWidth();
				GameCell.this.getHeight();
				GameCell.this.setFont(new Font("Courier New", m_changeable?Font.PLAIN:Font.BOLD,
						(GameCell.this.getWidth() + GameCell.this.getHeight()) / 4));
			}
		});
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (m_changeable) {
					if(e.getButton()==MouseEvent.BUTTON1){//�������
						//��������ѡ���
						Constant.inputPad.setLocation(GameCell.this,GameCell.this.getX()-GameCell.this.getWidth()*2/3,
								GameCell.this.getY()-GameCell.this.getHeight()*2/3,
								GameCell.this.getWidth()*2,GameCell.this.getHeight()*2);
					}
					if(e.getButton()==MouseEvent.BUTTON3){//�Ҽ�����������
						if(Constant.a[x][y]!=0)
							Constant.holesleft++;
						Constant.a[x][y]=0;
						setText("");
					}
				}
				System.out.println(x+" "+y);
			}
		});
	}
	public void refresh(boolean show) {
		int whichGrounp=(x/3*3+y/3*3)%2;//���������ĸ�����
		int rgbr,rgbg,rgbb;
		rgbr=whichGrounp==1?255:240;//����������ɫ
		rgbg=255;
		if(show){	//ִ�и���
			if(Constant.a[x][y]!=0){//��ʼ��Ϊ�㣬���ɸ���
				m_changeable=false;
				rgbb=255;
				setText(new Integer(Constant.a[x][y]).toString());
			}else{	//��ʼΪ�㣬����Ҫ��Ŀ�
				m_changeable=true;
				rgbb=220;
				setText(" ");
			}
		}else{	//��ִ�и���
			m_changeable=false;
			rgbb=255;
			setText("");
		}
		setBackground(new Color(rgbr,rgbg,rgbb));
	}
	public void setByInput(int num){	//��InputPad���ã���ȡ����
		if(Constant.a[x][y]==0)
			Constant.holesleft--;
		Constant.a[x][y]=0;
		if(!MySudokuGen.checkOne(x, y, num))	//���д�����ʾΪ��ɫ
			setForeground(Color.RED);
		else
			setForeground(Color.BLACK);
		setFont(new Font("Courier New", m_changeable?Font.PLAIN:Font.BOLD,
				(GameCell.this.getWidth() + GameCell.this.getHeight()) / 4));
		Constant.a[x][y]=num;
		setText(num+"");
		if(Constant.holesleft==0)	//�����궴�����жϽ��
			Constant.checkAnswer();
	}
	public boolean isChangeable(){
		return m_changeable;
	}
}