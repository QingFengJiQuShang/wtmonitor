package com.jrsoft.fri.cs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.Insets;
import java.awt.Toolkit;
import javax.swing.JDialog;

/**
 * @author Administrator �˹������÷���ʵ���������󣬵��� void show("����","����") ����. InfoUtil tool
 *         = new InfoUtil(); tool.show("����","����")
 */
public class Tip {
	private Point oldP; // ��һ������,�϶�����ʱ��
	private TipWindow tw = null; // ��ʾ��
	private ImageIcon img = null; // ͼ�����
	private JLabel imgLabel = null; // ����ͼƬ��ǩ
	private JPanel headPan = null;
	private JPanel feaPan = null;
	private JPanel btnPan = null;
	private JLabel title = null; // ��Ŀ����
	private JLabel head = null; // ��ɫ����
	private JLabel close = null; // �رհ�ť
	private JTextArea feature = null; // ����
	private JScrollPane jfeaPan = null;
	private JLabel releaseLabel = null; // ����ʱ��
	private JLabel sure = null;
	private String titleT = null;
	private String word = null;
	private String time = null;

	// private SimpleDateFormat sdf = new
	// SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public void init() {
		// �½�300x220����Ϣ��ʾ��
		tw = new TipWindow(300, 220);
		img = new ImageIcon("E:\\Workspaces\\GitHub\\wtmonitor\\WebRoot\\img\\index1.png");
		imgLabel = new JLabel(img);
		// ���ø������Ĳ����Լ�����пؼ��ı߽�
		headPan = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		feaPan = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		btnPan = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		title = new JLabel("���ݱ���");
		head = new JLabel(titleT);
		close = new JLabel(" x");
		feature = new JTextArea(word);
		jfeaPan = new JScrollPane(feature);
		// releaseLabel = new JLabel("������  " + time);
		sure = new JLabel("ȷ��");
		sure.setBackground(Color.red);	
		sure.setHorizontalAlignment(SwingConstants.CENTER);
		ImageIcon icon = new ImageIcon("E:\\Workspaces\\GitHub\\wtmonitor\\WebRoot\\img\\add.png");
		sure.setIcon(icon);
		
		// �������������Ϊ͸�������򿴲�������ͼƬ
		((JPanel) tw.getContentPane()).setOpaque(false);
		headPan.setOpaque(false);
		feaPan.setOpaque(false);
		btnPan.setOpaque(false);

		// ����JDialog����������ͼƬ
		tw.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
		imgLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		headPan.setPreferredSize(new Dimension(300, 40));

		// ������ʾ��ı߿�,���Ⱥ���ɫ
		tw.getRootPane().setBorder(
				BorderFactory.createMatteBorder(1, 1, 1, 1, Color.gray));
		title.setPreferredSize(new Dimension(260, 26));
		title.setVerticalTextPosition(JLabel.CENTER);
		title.setHorizontalTextPosition(JLabel.CENTER);
		title.setFont(new Font("����", Font.PLAIN, 20));
		title.setForeground(Color.black);

		close.setFont(new Font("Arial", Font.BOLD, 15));
		close.setPreferredSize(new Dimension(20, 20));
		close.setVerticalTextPosition(JLabel.CENTER);
		close.setHorizontalTextPosition(JLabel.CENTER);
		close.setCursor(new Cursor(12));
		close.setToolTipText("�ر�");

		head.setPreferredSize(new Dimension(250, 35));
		head.setVerticalTextPosition(JLabel.CENTER);
		head.setHorizontalTextPosition(JLabel.CENTER);
		head.setFont(new Font("����", Font.PLAIN, 20));
		head.setForeground(Color.BLACK);

		feature.setEditable(false);
		feature.setForeground(Color.BLACK);
		feature.setFont(new Font("����", Font.PLAIN, 16));
		feature.setBackground(new Color(255, 255, 255));
		// �����ı����Զ�����
		feature.setLineWrap(true);

		jfeaPan.setPreferredSize(new Dimension(250, 250));
		jfeaPan.setBorder(null);
		jfeaPan.setBackground(Color.black);

		 

		// Ϊ�������ı��򣬼Ӹ��յ�JLabel������������ȥ
		JLabel jsp = new JLabel();
		jsp.setPreferredSize(new Dimension(300, 5));

		sure.setPreferredSize(new Dimension(110, 30));
		// ���ñ�ǩ�������
		sure.setCursor(new Cursor(12));

		// headPan.add(title);
		headPan.add(head);
		headPan.add(close);

		feaPan.add(jsp);
		feaPan.add(jfeaPan);
		// feaPan.add(releaseLabel);

		btnPan.add(sure);

		tw.add(headPan, BorderLayout.NORTH);
		tw.add(feaPan, BorderLayout.CENTER);
		tw.add(btnPan, BorderLayout.SOUTH);
	}

	public void handle() {
		// Ϊ���°�ť������Ӧ���¼�
		
		sure.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				 //JOptionPane.showMessageDialog(tw, "лл���ټ�");
				tw.close();
			}

			public void mouseEntered(MouseEvent e) {
				sure.setBorder(BorderFactory.createLineBorder(Color.gray));
			}

			public void mouseExited(MouseEvent e) {
				sure.setBorder(null);
			}
		});
		// ��������϶��¼�
		title.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				Point newP = new Point(e.getXOnScreen(), e.getYOnScreen());
				int x = tw.getX() + (newP.x - oldP.x);
				int y = tw.getY() + (newP.y - oldP.y);
				tw.setLocation(x, y);
				oldP = newP;
			}
		});
		// ��갴��ʱ��ʼ����,���϶�ʱ������
		title.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				oldP = new Point(e.getXOnScreen(), e.getYOnScreen());
			}
		});
		// ���Ͻǹرհ�ť�¼�
		close.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				tw.close();
			}

			public void mouseEntered(MouseEvent e) {
				close.setBorder(BorderFactory.createLineBorder(Color.gray));
			}

			public void mouseExited(MouseEvent e) {
				close.setBorder(null);
			}
		});
	}

	public void show(String titleT, String word) {
		this.titleT = titleT;
		this.word = word;
		// time = sdf.format(new Date());
		init();
		handle();
		tw.setAlwaysOnTop(true);
		tw.setUndecorated(true);
		tw.setResizable(false);
		tw.setVisible(true);
		tw.run();
	}
}

class TipWindow extends JDialog {
	private static final long serialVersionUID = 8541659783234673950L;
	private static Dimension dim;
	private int x, y;
	private int width, height;
	private static Insets screenInsets;

	public TipWindow(int width, int height) {
		this.width = width;
		this.height = height;
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(
				this.getGraphicsConfiguration());
		x = (int) (dim.getWidth() - width - 3);
		y = (int) (dim.getHeight() - screenInsets.bottom - 3);
		initComponents();
	}

	public void run() {
		for (int i = 0; i <= height; i += 10) {
			try {
				this.setLocation(x, y - i);
				Thread.sleep(5);
			} catch (InterruptedException ex) {
			}
		}
		// �˴���������ʵ������Ϣ��ʾ��5����Զ���ʧ
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	//	close();
	}

	private void initComponents() {
		this.setSize(width, height);
		this.setLocation(x, y);
		this.setBackground(Color.black);
	}

	public void close() {
		x = this.getX();
		y = this.getY();
		int ybottom = (int) dim.getHeight() - screenInsets.bottom;
		for (int i = 0; i <= ybottom - y; i += 10) {
			try {
				setLocation(x, y + i);
				Thread.sleep(5);
			} catch (InterruptedException ex) {
			}
		}
		dispose();
	}

}
