import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public class MyMenuItem extends JMenuItem{
	public MyMenuItem(String title,int Msg){//���캯������������Ϣ
		super(title);
		setFont(new Font("Sans-Serif",Font.PLAIN,16));
		this.addActionListener(new ActionListener() {//�����Ӧ	
			@Override
			public void actionPerformed(ActionEvent e) {
				//��Ϊѡ���Ѷ�
				if(Msg>=Constant.Msg.Easy&&Msg<=Constant.Msg.Evil){
					Constant.dfct=Msg;
				}
				else{	//��Ϊ����
					switch(Msg){
					case Constant.Msg.New:
						Constant.newGame();
						break;
					case Constant.Msg.Show:
						Constant.showAnswer();
						break;
					case Constant.Msg.Exit:
						System.exit(0);
						break;
					}
				}
			}
		});
	}
}
