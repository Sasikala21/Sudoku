import java.util.Random;

public class MySudokuGen {
	static int[] holesChecked;
	public static void generate(){//����������Ŀ
		int outerIndex;		//�����ڵ����к�����Ϊһ��
		int index;			//�������
		int indexA,indexB;	//ʵ�����
		int times;			//���д��Ҳ����Ĵ���
		Random rand=new Random();
		times=rand.nextInt(10)+20;	//���Ҳ�������
		for (int i = 0; i < times; i++){
			outerIndex=rand.nextInt(3);	//������
		    index=rand.nextInt(3);	//�������
		    indexA=outerIndex*3+index;	//ʵ�����
		    indexB=outerIndex*3+(index+1)%3;//ʵ�����
	        switch(rand.nextInt(6)){
	        case 0: 
	        	SwapRow(indexA,indexB);	//�����У����ڣ�
	        	//System.out.println("SwapRow("+indexA+","+indexB+")");
	        	break;
	        case 1: 
	        	SwapCol(indexA,indexB);	//�����У����ڣ�
	        	//System.out.println("SwapCol("+indexA+","+indexB+")");
	        	break;
	        case 2: 
	        	SwapBigRow(outerIndex);	//�����齻��
	        	//System.out.println("SwapBigRow("+outerIndex+")");
	        	break;
	        case 3: 
	        	SwapBigCol(outerIndex);	//�����齻��
	        	//System.out.println("SwapBigCol("+outerIndex+")");
	        	break;
	        default://���ֽ����ĸ��ʸ���
	        	SwapDigits();			//��������
	        	//System.out.println("SwapDigits()");
	        	break;
	        }
	        
	    }
		Constant.answer=new int[9][9];
	    for(int i=0;i<9;i++){
	    	for(int j=0;j<9;j++){
	    		Constant.answer[i][j]=Constant.a[i][j];
	    	}
	    }
		//Constant.printA();
	    DigHole();
		for(int k=0;k<9;k++){
			for(int j=0;j<9;j++){
				System.out.print(Constant.a[k][j]);
			}
			System.out.print('\n');
		}
		System.out.print('\n');
	}
	
	private static void SwapDigits(){
		Random rand=new Random();
		int digit1,digit2;
		digit1=rand.nextInt(9);
		while((digit2=rand.nextInt(9))==digit1);
		digit1++;
		digit2++;
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				if(Constant.a[i][j]==digit1)Constant.a[i][j]=digit2;
				else if(Constant.a[i][j]==digit2)Constant.a[i][j]=digit1;
			}
		}
		
	}
	private static void SwapRow(int RowA,int RowB)
	{
		
	    int[] temp=Constant.a[RowA];
	    Constant.a[RowA]=Constant.a[RowB];
	    Constant.a[RowB]=temp;
	}

	private static void SwapCol(int ColA,int ColB)
	{
	    int temp=0;
	    for(int i=0;i<9;i++)
	    {
	        temp=Constant.a[i][ColA];
	        Constant.a[i][ColA]=Constant.a[i][ColB];
	        Constant.a[i][ColB]=temp;
	    }
	}

	private static void SwapBigRow(int outerIndex)
	{
	    int RowA=outerIndex;
	    int RowB=(outerIndex+1)%3;
	    SwapRow(RowA*3,RowB*3);
	    SwapRow(RowA*3+1,RowB*3+1);
	    SwapRow(RowA*3+2,RowB*3+2);
	}

	private static void SwapBigCol(int outerIndex)
	{
	    int ColA=outerIndex;
	    int ColB=(outerIndex+1)%3;
	    SwapCol(ColA*3,ColB*3);
	    SwapCol(ColA*3+1,ColB*3+1);
	    SwapCol(ColA*3+2,ColB*3+2);
	}
	
	private static void DigHole() {
		// TODO Auto-generated method stub
		Constant.holes=0;
		int direct=1;	//��������ķ���
		int[] blockHoles={0,0,0,0,0,0,0,0,0};	//ÿһ�����ڵĶ�������
		holesChecked=new int[2*Constant.Diffculty.holesTotal[Constant.dfct+1]];	//��������
		boolean[][] checked=new boolean[9][9];	//���Ƿ񱻼���
		int checkedCount;	//�������ĵ�ĸ���
		Random rand=new Random();
		int x,y;
		int maxHoles=rand.nextInt(	//������������
				Constant.Diffculty.holesTotal[Constant.dfct+1]-
				Constant.Diffculty.holesTotal[Constant.dfct])
				+Constant.Diffculty.holesTotal[Constant.dfct];
		x=rand.nextInt(9);
		y=rand.nextInt(9);
		for(Constant.holes=0,checkedCount=0;Constant.holes<maxHoles&&checkedCount<=81;){
			int centerNo=getCenter(x)/3*3+getCenter(y)/3;	//��ø�����ı��
			if(blockHoles[centerNo]<Constant.Diffculty.holesEach[Constant.dfct+1]){
				//ÿ�����������δ�ﵽ���������
				if(!checked[x][y]){	//û������
					if(checkUnique(x,y)){	//����Ƿ����Ψһ��
						Constant.a[x][y]=0;
						holesChecked[Constant.holes*2]=x;	//�洢��������
						holesChecked[Constant.holes*2+1]=y;
						Constant.holes++;
						blockHoles[centerNo]++;
						/*System.out.println("Checked:"+x+" "+y+" "+holesChecked[Constant.holes*2]+" "+
								holesChecked[Constant.holes*2+1]+Constant.holes);*/
					}
					checked[x][y]=true;	//��Ǽ��
					checkedCount++;
				}
			}
			int sel=rand.nextInt(5);//Ϊ�����ȵز����������֮һ�ĸ����������
			if(sel==0){
				x=rand.nextInt(9);
				y=rand.nextInt(9);
			}else{
				switch(Constant.dfct){
				case Constant.Diffculty.Easy://��ģʽȫ���
					x=rand.nextInt(9);
					y=rand.nextInt(9);
					break;
				case Constant.Diffculty.Medium://�е�ģʽ�������
					direct=(x+y)%2==0?1:-1;
					if(y!=8&&y!=1)
						y=(x%2)==0?(y+2*direct):(y-2*direct);
					else{
						y-=1;
						x+=1;
					}
					if(x>8){
						direct=-direct;
						x-=9;
					}
					break;
				case Constant.Diffculty.Hard://����ģʽ֮���η���
					if(y==8&&x%2==0){	//��ͷ����䣬������һ��
						x++;
					}else if(y==0&&x%2==1){ //��ͷ����䣬������һ��
						x++;
					}else{
						y=x%2==0?y+1:y-1;	//���������
					}
					if(x>8){
						x=rand.nextInt(9);
						y=rand.nextInt(9);
					}
					break;
				case Constant.Diffculty.Evil://��̬ģʽѭ������
					if(y==8)
						x=(x+1)%9;
					y=(y+1)%9;
					break;
				default:break;
				}
			}
		}
		for(int i=0;i<Constant.holes;i++){
			Constant.a[holesChecked[i*2]][holesChecked[i*2+1]]=0;
		}
		Constant.printA();
	}
	private static void DigHole2() {
		// TODO Auto-generated method stub
		Constant.holes=0;
		int direct=1;
		int[] blockHoles={0,0,0,0,0,0,0,0,0};
		holesChecked=new int[2*Constant.Diffculty.holesTotal[Constant.dfct+1]];
		boolean[][] checked=new boolean[9][9];
		int checkedCount;
		Random rand=new Random();
		int x,y;
		int maxHoles=rand.nextInt(
				Constant.Diffculty.holesTotal[Constant.dfct+1]-
				Constant.Diffculty.holesTotal[Constant.dfct])
				+Constant.Diffculty.holesTotal[Constant.dfct];
		if(Constant.dfct==Constant.Diffculty.Easy){
			x=rand.nextInt(9);
			y=rand.nextInt(9);
		}else{
			x=0;y=0;
		}
		for(Constant.holes=0,checkedCount=0;Constant.holes<maxHoles&&checkedCount<=81;){
			int centerNo=getCenter(x)/3*3+getCenter(y)/3;
			if(blockHoles[centerNo]<Constant.Diffculty.holesEach[Constant.dfct+1]){
				if(!checked[x][y]){
					if(checkUnique(x,y)){
						Constant.a[x][y]=0;
						holesChecked[Constant.holes*2]=x;
						holesChecked[Constant.holes*2+1]=y;
						Constant.holes++;
						blockHoles[centerNo]++;
						/*System.out.println("Checked:"+x+" "+y+" "+holesChecked[Constant.holes*2]+" "+
								holesChecked[Constant.holes*2+1]+Constant.holes);*/
					}
					checked[x][y]=true;
					checkedCount++;
				}
			}
			switch(Constant.dfct){
			case Constant.Diffculty.Easy:
				x=rand.nextInt(9);
				y=rand.nextInt(9);
				break;
			case Constant.Diffculty.Medium:
				direct=(x+y)%2==0?1:-1;
				if(y!=8&&y!=1)
					y=(x%2)==0?(y+2*direct):(y-2*direct);
				else{
					y-=1;
					x+=1;
				}
				if(x>8){
					//direct=-direct;
					x-=9;
				}
				break;
			case Constant.Diffculty.Hard:
				if(y==8&&x%2==0){
					x++;
				}else if(y==0&&x%2==1){
					x++;
				}else{
					y=x%2==0?y+1:y-1;
				}
				if(x>8){
					x=rand.nextInt(9);
					y=rand.nextInt(9);
				}
				break;
			case Constant.Diffculty.Evil:
				if(y==8)
					x=(x+1)%9;
				y=(y+1)%9;
				break;
			default:break;
			}
		}
		for(int i=0;i<Constant.holes;i++){
			Constant.a[holesChecked[i*2]][holesChecked[i*2+1]]=0;
		}
		Constant.printA();
	}
	private static boolean checkUnique(int x,int y){	//�ڿ׺�,�����Ψһ��
		int tmp=Constant.a[x][y];
		for(int i=1;i<=9;i++){
			if(i!=tmp){	//�ò�����ԭ���������滻�����ɹ���֤����Ψһ
 				if(checkOne(x,y,i)){	//����������ﲻ������ͻ
					Constant.a[x][y]=i;	//��ʼ����
					if(check(0)){	//����Ƿ��н�
						Constant.a[x][y]=tmp;
						return false;
					}
				}
			}
		}
		Constant.a[x][y]=0;	//��Ψһ�����ڿ�
		return true;
	}
	private static boolean check(int index){	//�ӵ�index������ʼ����Ƿ��н�
		if(index<Constant.holes){	//��û�������������򷵻���
			for(int i=1;i<=9;i++){	//��ÿ�ֿ��ܴ����Խ�
				if(checkOne(holesChecked[index*2],holesChecked[index*2+1],i)){	//������
					Constant.a[holesChecked[index*2]][holesChecked[index*2+1]]=i;//����
					if(check(index+1)){	//����ʼ����һ����, ����������֤��������һ��
						Constant.a[holesChecked[index*2]][holesChecked[index*2+1]]=0;//�ָ�ԭʼ״̬
						return true;
					}
				}
			}
			Constant.a[holesChecked[index*2]][holesChecked[index*2+1]]=0;//�ָ�ԭʼ״̬
			return false;
		}
		System.out.println("ok");
		Constant.printAnswer();
		Constant.printA();
		return true;
	}
	public static boolean checkOne(int current_i, int current_j, int num) {// ���(i,j)λ��num�ھ������Ƿ�Ϸ�
		for (int i = 0; i < 9; i++) {// ��һ�����������
			if (Constant.a[i][current_j] == num) {
				return false;
			}
		}
		for (int j = 0; j < 9; j++) {// �ڶ������������
			if (Constant.a[current_i][j] == num) {
				return false;
			}
		}
		// ������������ڵ�С����
		int center_i = getCenter(current_i);
		int center_j = getCenter(current_j);
		if(Constant.a[center_i - 1][center_j - 1]==num||
				Constant.a[center_i][center_j - 1]==num||
				Constant.a[center_i + 1][center_j - 1]==num||
				Constant.a[center_i - 1][center_j]==num||
				Constant.a[center_i][center_j]==num||
				Constant.a[center_i + 1][center_j]==num||
				Constant.a[center_i - 1][center_j + 1]==num||
				Constant.a[center_i][center_j + 1]==num||
				Constant.a[center_i + 1][center_j + 1]==num)
			return false;

		return true;
	}

	private static int getCenter(int n) {
		return n/3*3+1;
	}
}
