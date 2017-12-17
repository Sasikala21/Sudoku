import java.awt.GridLayout;
import javax.swing.JPanel;
public class GamePanel extends JPanel{
	GameCell[][] cells;
	public GamePanel(){
		initBtns();
	}
	private void initBtns(){	//初始化按钮
		cells=new GameCell[9][9];
		setLayout(new GridLayout(9,9));
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				cells[i][j]=new GameCell(i,j);
				/*cells[i].setBorder(BorderFactory.createLineBorder(Color.blue));*/
			add(cells[i][j]);
			}
		}
	}
	public void newGame(){	//新游戏
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				cells[i][j].refresh(true);
			}
		}
	}
	public void showResult(){	//显示结果
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				if(cells[i][j].isChangeable()){
					Constant.a[i][j]=Constant.answer[i][j];
					cells[i][j].setText(Constant.a[i][j]+"");
				}
			}
		}
	}
}
