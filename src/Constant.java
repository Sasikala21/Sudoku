public class Constant {
	static GamePanel gamePanel;
	static MainFrame wnd;
	static int holes;
	static int holesleft;
	public static class Msg{	//��Ϸ��Ϣ
		static final int Easy=0;
		static final int Medium=1;
		static final int Hard=2;
		static final int Evil=3;
		static final int Max=4;
		static final int New=5;
		static final int Show=6;
		static final int Exit=7;
	}
	public static class Diffculty{	//��Ϸ�Ѷ����
		static final int Easy=0;
		static final int Medium=1;
		static final int Hard=2;
		static final int Evil=3;
		static final int Max=4;
		static final int[] holesTotal={32,46,50,54,60};	//��������
		static final int[] holesEach={4,5,6,7,9};	//ÿ���������Ķ�������
	}
	static int dfct;
	static int[][] a;
	static int[][] answer;
	final static InputPad inputPad=new InputPad();
	public static boolean checkAnswer(){	//��Ϸ���������
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				if(a[i][j]!=answer[i][j])
					return false;
			}
		}
		wnd.success();
		return true;
		
	}
	public static void newGame(){	//����Ϸ
		int[][]tmp={
				{1,2,3,4,5,6,7,8,9},
				{7,8,9,1,2,3,4,5,6},
				{4,5,6,7,8,9,1,2,3},
				
				{2,3,4,5,6,7,8,9,1},
				{8,9,1,2,3,4,5,6,7},
				{5,6,7,8,9,1,2,3,4},
				
				{3,4,5,6,7,8,9,1,2},
				{9,1,2,3,4,5,6,7,8},
				{6,7,8,9,1,2,3,4,5}
		};
		a=tmp;
		MySudokuGen.generate();
		holesleft=holes;
		gamePanel.newGame();
	}
	public static void showAnswer(){	//��ʾ���
		gamePanel.showResult();
	}
	public static void printA(){	//�����Ŀ��������
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				System.out.print(a[i][j]);
			}
			System.out.println();
		}
		System.out.println();
		
	}
	public static void printAnswer(){//��������������
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				System.out.print(answer[i][j]);
			}
			System.out.println();
		}
		System.out.println();
		
	}
}