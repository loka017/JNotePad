package notepad;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import javafx.scene.text.Font;
/**
 * [AWT] Frame   .add(...);
 * 
 * [Swing] JFframe  .getContentPane().add(....)
 * 
 * @author ATOVAC_JESS
 *
 */
public class JNotePad extends JFrame {

	File rootDir = new File(".");
	
	JTextArea noteArea = null;
	File currentFile = null;
	
	public JNotePad() {
		super("노트패드");
		this.setSize(600, 400);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container root = this.getContentPane();
//		
		JButton btnSave = new JButton("DDD");
		root.add(btnSave, BorderLayout.SOUTH);
		/*
		JTextArea noteArea = new JTextArea();
		// JScrollPane scroll1 = new JScrollPane(noteArea);
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(noteArea);
		root.add(scroll, BorderLayout.CENTER);
		*/
		
		
		installMenu();
		// add(root);
		// this.revalidate();
		
//		this.setContentPane(root);
		// this.setVisible(true);
		
//		JPanel root = new JPanel();
//		root.setLayout(new BorderLayout());
//		root.add(new JButton("OK"));
//		this.setContentPane(root);;
		
		
	}
	void installMenu() {
		JMenuBar menuBar = new JMenuBar();
		{
			JMenu mfile = new JMenu("File");
			menuBar.add(mfile);
			{
				JMenuItem newFile = new JMenuItem("New");
				mfile.add(newFile);
				
				JMenuItem openFile = new JMenuItem("Open");
				openFile.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						actionOpenFile();
					}
				});
				mfile.add(openFile);
				
				JMenuItem saveFile = new JMenuItem("Save");
				mfile.add(saveFile);
			}
		}
		{
			JMenu mview = new JMenu("View");
			menuBar.add(mview);
			{
				JMenuItem ddd = new JMenuItem("DDD");
				mview.add(ddd);
			}
		}
		this.setJMenuBar(menuBar);
		
	}
	void updateNoteArea(File f) {
		try {
			String content = readFile2(f);
			System.out.println(content);
			
			if( noteArea == null) {
				
				noteArea = new JTextArea();
				// JScrollPane scroll1 = new JScrollPane(noteArea);
				JScrollPane scroll = new JScrollPane();
				scroll.setViewportView(noteArea);
				getContentPane().add(scroll, BorderLayout.CENTER);
				getContentPane().revalidate();
			}
			
			noteArea.setText(content);
			
		} catch (IOException e) {
			System.out.println("오류 메세지!");
			JOptionPane.showMessageDialog(this, "파일을 읽어올 수 없습니다");
			e.printStackTrace();
		}
	}
	
	void actionNewFile() {
		;
	}
	/**
	 * actionXXXX
	 */
	void actionOpenFile() {
		// 1. 파일 경로 맏아서
		JFileChooser chooser = new JFileChooser(rootDir);
		int response = chooser.showOpenDialog(this);
		if ( response != JFileChooser.APPROVE_OPTION) {
			return;
		}
		
		File target = chooser.getSelectedFile(); // File
		this.currentFile = target;
		
		// File s = new File("c:/aeksl/skeke"); // Path
		// s.exist
		// System.out.println(s.getPath());
		System.out.println(target.getAbsolutePath());
		
		updateNoteArea(target);
		
	}
	
	void writeFile(String content) {
		
	}
	
	String readFile2(File f) throws IOException {
		// jdk 1.7 이상
		// Files.write
		byte [] data = Files.readAllBytes(f.toPath());
		String s = new String(new byte[] { 'd', 'k', 'd', 'k'} );//String s= "dkd";
		String fileContent  =  new String(data);
		return fileContent;
	}
	
	String readFile(File f) throws IOException {
		if ( f.isDirectory()) {
			throw new RuntimeException("파일이 아닙니다");
		}
		// byte 1 
		// char 2
		// FileInputStream fis =new FileInputStream(f); // fopen(ddd)
		// 
		FileReader fr = new FileReader(f); 
		int c;
		StringBuilder sb = new StringBuilder();
		while ( (c=fr.read()) != -1) {
			sb.append((char)c);
		}
		return sb.toString();
	}
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		 new JNotePad().setVisible(true);;
	}
}
