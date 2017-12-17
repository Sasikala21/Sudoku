import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public class MyMenuItem extends JMenuItem{
	public MyMenuItem(String title,int Msg){//构造函数传入标题和消息
		super(title);
		setFont(new Font("Sans-Serif",Font.PLAIN,16));
		this.addActionListener(new ActionListener() {//添加响应	
			@Override
			public void actionPerformed(ActionEvent e) {
				//若为选择难度
				if(Msg>=Constant.Msg.Easy&&Msg<=Constant.Msg.Evil){
					Constant.dfct=Msg;
				}
				else{	//若为控制
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
