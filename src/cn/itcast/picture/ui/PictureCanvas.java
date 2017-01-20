package cn.itcast.picture.ui;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/*
 * ƴͼ��
 */
public class PictureCanvas extends JPanel implements MouseListener{
	//��̬����
	public static int pictureID = 1;//ͼƬID
	public static int stepNum = 0; //����
	
	//��Ա����
	private Cell [] cell;//С����
	private boolean hasAddActionListener = false; //������ʾ�Ƿ�Ϊ��������˵�������������ֵΪtrue��δ���Ϊfalse
	private Rectangle nullCell; 
	
	//���췽��
	public PictureCanvas(){
		//����ƴͼ���Ĳ���
		this.setLayout(null);
		//����12��ͼƬС������ӵ�ƴͼ��
	    cell = new Cell[12];
		for (int i = 0; i < 4; i++) { //�����������
			for(int j=0; j<3; j++){ //�����������
				//����ͼƬ
//				ImageIcon icon = new ImageIcon("picture\\1_1.gif");
				ImageIcon icon = new ImageIcon("picture\\"+pictureID+"_"+(i*3+j+1)+".gif");
				//����ͼƬС����
				cell [i*3+j] = new Cell(icon);
				//ָ��ͼƬС������ʾ��λ��
				cell[i*3+j].setLocation(20+j*150, 20+i*150);
				//��ͼƬС������ӵ�ͼƬƴͼ����ʾ
				this.add(cell[i*3+j]);
			}
		}
		//ɾ����12��ͼƬС����
		this.remove(cell[11]);
		nullCell = new Rectangle(300+20,450+20,150,150);
	}
	
	//���¼���ͼƬ�������������ʾ
	public void reLoadPictureAddNumber(){
		//��ȡ��ÿһ��ͼƬС����
		for(int i=0; i<4; i++){//����
			for(int j=0; j<3; j++){//����
				//��ȡС����cell[i*3+j]
				
				//����С������ʾ��ͼƬ
				ImageIcon icon = new ImageIcon("picture\\"+pictureID+"_"+(i*3+j+1)+".gif");
				cell[i*3+j].setIcon(icon);
				//����С������ʾ��������ʾ
				cell[i*3+j].setText("" + (i*3+j+1));
				//����С����������ʾ��λ��
				cell[i*3+j].setVerticalTextPosition(this.getY()/2);
				cell[i*3+j].setHorizontalTextPosition(this.getX()/2);
			}
		}
	}
	
	//���¼���ͼƬ����ȥ��������ʾ
	public void reLoadPictureClearNumber(){
		//��ȡ��ÿһ��ͼƬС����
		for(int i=0; i<4; i++){//����
			for(int j=0; j<3; j++){//����
				//��ȡС����cell[i*3+j]
				
				//����С������ʾ��ͼƬ
				ImageIcon icon = new ImageIcon("picture\\"+pictureID+"_"+(i*3+j+1)+".gif");
				cell[i*3+j].setIcon(icon);
				//����С������ʾ��������ʾ
				cell[i*3+j].setText("");
			}
		}
	}
	
	//��С�����������λ�����򣬴���˳��
	public void start(){
		//���Ҫ��û�и�С�����������������Ļ�������Ӽ���
		if(!hasAddActionListener){
			//��Ӽ���
			for(int i = 0; i<11; i++){
				cell[i].addMouseListener(this);
			}
			
			//���������������״̬
			hasAddActionListener = true;
		}
		//�жϵ�ǰ��һ��С����������ϽǵĽϽ���ʱ�򣬽��з�����շ���Ļ���
		//�����һ��С���������Ͻǵ��ĸ�����λ���ڣ��Ͳ��ϵ�ѭ�������з�����շ���λ�õĻ���
		while(cell[0].getBounds().x <= 170 && cell[0].getBounds().y<=170){
			//��ȡ���շ����λ��
			int nullX = nullCell.getBounds().x;
			int nullY = nullCell.getBounds().y;
			
			//�������һ�����򣬽��пշ�������ͨ��С����Ļ�������
			//����0-3֮������������Ӧ�շ�������������ƶ�
			int direction = (int)(Math.random()*4); //0,1,2,3
			switch (direction) {
			case 0://�շ��������ƶ�������ߵķ�����л���λ�ã����ķ���Ҫ�����ƶ�
//				nullX = nullX - 150;
				nullX -= 150;
				cellMove(nullX,nullY,"RIGHT");
				break;
			case 1://�շ��������ƶ������ұߵķ�����л���λ�ã��Ҳ�ķ���Ҫ�����ƶ�
				nullX += 150;
				cellMove(nullX,nullY,"LEFT");
				break;
			case 2://�շ��������ƶ������ϱߵķ�����л���λ�ã��ϲ�ķ���Ҫ�����ƶ�
				nullY -= 150;
				cellMove(nullX,nullY,"DOWN");
				break;
			case 3://�շ��������ƶ������±ߵķ�����л���λ�ã��²�ķ���Ҫ�����ƶ�
				nullY += 150;
				cellMove(nullX,nullY,"UP");
				break;
			default:
				break;
			}
		}
	}
	/**
	 * /�շ��������ƶ�������ߵķ�����л���λ�ã����ķ���Ҫ�����ƶ�
	 * @param nullX �շ����X������
	 * @param nullY �շ����Y������
	 * @param string ����Ҫ�ƶ��ķ���
	 */
	private void cellMove(int nullX, int nullY, String direction) {
		for(int i=0; i<11; i++){
			//��ȡ����շ���λ����ͬ��С����
			if(cell[i].getBounds().x == nullX && cell[i].getBounds().y == nullY){
				//��ǰ������ƶ�
				cell[i].move(direction);
				//�շ�����ƶ�
				nullCell.setLocation(nullX,nullY);
				//����λ�ú���ܵ�ǰѭ��
				break;
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) { //��갴��ȥ
		//��ȡ����ǰ�������С����
		Cell button = (Cell) e.getSource();
		//��ȡ����������X��Y����
		int clickX = button.getBounds().x;
		int clickY = button.getBounds().y;
		//��ȡ��ǰ�շ����X��Y����
		int nullX = nullCell.getBounds().x;
		int nullY = nullCell.getBounds().y;
		//���бȽϣ�������������Ľ���λ�õĻ���
		if(clickX == nullX && clickY - nullY == 150){ //�����Ϊ�շ�������ķ���
			button.move("UP"); //������ķ��������ƶ�
		} else if(clickX == nullX && clickY - nullY == -150){ //�����Ϊ�շ�������ķ���
			button.move("DOWN");
		} else if(clickX - nullX == 150 && clickY == nullY){ //�����Ϊ�շ�����ұ�
			button.move("LEFT");
		} else if(clickX - nullX == -150 && clickY == nullY){ //�������Ϊ�շ�������
			button.move("RIGHT");
		} else {
			return; //�������ƶ�����,�Ͳ������κεĴ���
		}
		//���¿ո��λ��
		nullCell.setLocation(clickX,clickY);
		//ƴͼ���������»���
		this.repaint();
		//���²���������Ϸ״̬���Ĳ�������
		stepNum++;
		PictureMainFrame.step.setText("������"+stepNum);
		
		//�жϵ�ǰ��Ϸ�Ƿ���ɣ�����ɣ������һ���Ѻ���ʾ
		if(hasAddActionListener){
			if(this.isFinish()){
				JOptionPane.showMessageDialog(this, "��ϲ�����ƴͼ�����ͣ�\n ���ò���"+stepNum);
				//����ÿһ��С������������������������С��������������
				for(int i=0; i<11; i++){
					cell[i].remove(this);
				}
				//���·���Ķ�����������״̬
				hasAddActionListener = false;
			}
		}
	}

	//�жϵ�ǰ��Ϸ�Ƿ����,���������ж��Ƿ�ƴͼ�ɹ�
	private boolean isFinish(){
		for(int i=0; i<11; i++){
			//��ȡÿһ�������λ��
			int x = cell[i].getBounds().x;
			int y = cell[i].getBounds().y;
			if((y-20)/150 * 3 + (x-20)/150 != i){
				// i=0,(y-20)/150 * 3 + (x-20)/150=0
				// i=0,(y-20)/150 * 3 + (x-20)/150=1
				//....
				// i=5,(y-20)/150 * 3 + (x-20)/150=5
				return false;
			}
		}
		return true;
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
