import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;
 
public class main { 
	
    public static void main(String[] args) throws FileNotFoundException { 
    	BackgroundPanel backpanel = new BackgroundPanel(new ImageIcon("picture//back.jpg").getImage());
        
        JFrame lotteryFrame = new JFrame("抽奖活动");
        lotteryFrame.setTitle("抽奖活动");
        lotteryFrame.setLocation(200, 50);
        lotteryFrame.setBackground(new Color(255));
        lotteryFrame.setSize(800, 600);
        lotteryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        lotteryFrame.setContentPane(backpanel);

        ImageIcon img = new ImageIcon("picture//icon.jpg");
        lotteryFrame.setIconImage(img.getImage());

        JLabel labelName = getlabelName();
        JLabel labelPic = getlabelPic();
        ArrayList<String> fileList= getFilelist();

        backpanel.add(BorderLayout.CENTER , labelPic);  
        backpanel.add(BorderLayout.NORTH, labelName);
        lotteryFrame.add(BorderLayout.SOUTH, getbutton(labelName, labelPic, fileList));
        lotteryFrame.setVisible(true);
    }
    
    public static JLabel getlabelName() {
    	JLabel label = new JLabel();
    	label.setHorizontalAlignment(0);
    	label.setVisible(true);
    	Font font = new Font("Courier", Font.BOLD,64);
    	label.setFont(font);
    	label.setForeground(Color.white);
    	return label;
    }
    
    public static JLabel getlabelPic() {
    	JLabel label = new JLabel();
    	label.setSize(500, 500);
    	label.setHorizontalAlignment(0);
    	label.setVisible(true);
    	return label;
    }
    
    public static JButton getbutton(JLabel labelName, JLabel labelPic, ArrayList<String> fileList) {
    	Font font = new Font("Courier", Font.BOLD,32);
    	JButton button = new JButton("开始");
    	button.setFont(font);
    	button.addActionListener( new ActionListener()
    	{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				button.setEnabled(false);
				new Thread() {
					public void run() {
						ImageIcon imageIcon;
						int i=0;
						for (String path : fileList) {
							labelName.setText(path.split("\\.")[0]);
							imageIcon = new ImageIcon(new ImageIcon("photo//" + path).getImage().getScaledInstance(400,
									400, Image.SCALE_DEFAULT));
							labelPic.setIcon(imageIcon);
							
							System.out.println(path);
							System.out.println("photo//" + path);
							System.out.println(i++);
							try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						int luckyguy = new Random().nextInt(fileList.size());
						labelName.setText(fileList.get(luckyguy).split("\\.")[0]);
						imageIcon = new ImageIcon(new ImageIcon("photo//" + fileList.get(luckyguy)).getImage().getScaledInstance(400,
								400, Image.SCALE_DEFAULT));
						labelPic.setIcon(imageIcon);
						fileList.remove(luckyguy);
						
						
						button.setEnabled(true);
					}
				}.start();
			}
    	});
    	return button;
    }
    
    public static ArrayList<String> getFilelist() throws FileNotFoundException{
    	File f = new File("photo");
    	ArrayList<String> list = new ArrayList<String>();
    	for(String file: f.list()) {
    	    list.add(file);
    	}
    	return list;
    }
}