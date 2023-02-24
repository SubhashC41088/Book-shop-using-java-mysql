import java.awt.EventQueue;


import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class JavaCrud {

	private JFrame frame;
	private JTextField txtbname;
	private JTextField txtedition;
	private JTextField txtprice;
	private JTable table;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JavaCrud window = new JavaCrud();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JavaCrud() {
		initialize();
		Connect();
		table_load();
	}
	
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	public void Connect() {
		 try {
			 Class.forName("com.mysql.jdbc.Driver");
			 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javacrud ","root","");
		 }
		 catch(ClassNotFoundException ex)
			 {
		 }
		 catch(SQLException ex) {
			 
		 }
	}
	
	
	public void table_load() {
		try {
			pst = con.prepareStatement("select * from book");
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	
	
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 716, 435);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("BOOK SHOP");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(252, 11, 161, 31);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "REGISTRATION", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(29, 53, 336, 194);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("BOOK NAME");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(28, 30, 96, 23);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("EDITION");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(28, 89, 96, 23);
		panel.add(lblNewLabel_1_1);
		
		txtbname = new JTextField();
		txtbname.setBounds(144, 31, 169, 23);
		panel.add(txtbname);
		txtbname.setColumns(10);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("PRICE");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(28, 143, 96, 23);
		panel.add(lblNewLabel_1_1_1);
		
		txtedition = new JTextField();
		txtedition.setColumns(10);
		txtedition.setBounds(144, 92, 169, 23);
		panel.add(txtedition);
		
		txtprice = new JTextField();
		txtprice.setColumns(10);
		txtprice.setBounds(144, 143, 169, 23);
		panel.add(txtprice);
		
		JButton btnNewButton = new JButton("SAVE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String bname,edition,price;
				
				bname = txtbname.getText();
				edition = txtedition.getText();
				price = txtprice.getText();
				
				
				try {
					pst = con.prepareStatement("insert into book(name,edition,price)values(?,?,?)");
				    pst.setString(1, bname);
				    pst.setString(2, edition);
				    pst.setString(3, price);
				    pst.executeUpdate();
				    JOptionPane.showMessageDialog(null, "Record Addedddd....");
				    table_load();
				    
				    txtbname.setText("");
				    txtedition.setText("");
				    txtprice.setText("");
				    txtbname.requestFocus();
				    
				}
				catch(SQLException el) {
					el.printStackTrace();
				}
				
				
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setBounds(29, 258, 89, 46);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("EXIT");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_1.setBounds(154, 258, 89, 46);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("CLEAR");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				txtbname.setText("");
			    txtedition.setText("");
			    txtprice.setText("");
			    txtbname.requestFocus();
				
				
				
				
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_2.setBounds(276, 258, 89, 46);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_2_1 = new JButton("UPDATE");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
              String bname,edition,price,bid;
				
				bname = txtbname.getText();
				edition = txtedition.getText();
				price = txtprice.getText();
				bid =textField.getText();
				
				try {
					pst = con.prepareStatement("update book set name=?,edition=?,price=? where id=?");
				    pst.setString(1, bname);
				    pst.setString(2, edition);
				    pst.setString(3, price);
				    pst.setString(4, bid);
				    pst.executeUpdate();
				    JOptionPane.showMessageDialog(null, "Record Updated....");
				    table_load();
				    
				    txtbname.setText("");
				    txtedition.setText("");
				    txtprice.setText("");
				  //  textField.setText("");
				    txtbname.requestFocus();
				    
				}
				catch(SQLException el) {
					el.printStackTrace();
				}
				
				
				
			}
				
				
				
				
			}
		);
		btnNewButton_2_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_2_1.setBounds(424, 312, 89, 46);
		frame.getContentPane().add(btnNewButton_2_1);
		
		JButton btnNewButton_2_2 = new JButton("DELETE");
		btnNewButton_2_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				
				 String bid;
					
					
					bid =textField.getText();
					
					try {
						pst = con.prepareStatement("delete from book where id=?");
					   
					    pst.setString(1, bid);
					    pst.executeUpdate();
					    JOptionPane.showMessageDialog(null, "Record Deleted....");
					    table_load();
					    
					    txtbname.setText("");
					    txtedition.setText("");
					    txtprice.setText("");
					  //  textField.setText("");
					    txtbname.requestFocus();
					    
					}
					catch(SQLException el) {
						el.printStackTrace();
					}
					
					
					
				}
					
					
					
					
				
				
			
		});
		btnNewButton_2_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_2_2.setBounds(570, 312, 89, 46);
		frame.getContentPane().add(btnNewButton_2_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(398, 53, 292, 248);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "SEARCH", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(29, 335, 336, 50);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("BOOK_ID");
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_2.setBounds(10, 16, 96, 23);
		panel_1.add(lblNewLabel_1_1_2);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				
				try {
					String id = textField.getText();
					
					pst = con.prepareStatement("select name,edition,price from book where id=?");
					pst.setString(1, id);
					ResultSet rs = pst.executeQuery();
					
					if(rs.next()==true)
					{
						String name = rs.getString(1);
						String edition = rs.getString(2);
						String price = rs.getString(3);
						
						txtbname.setText(name);
						txtedition.setText(edition);
						txtprice.setText(price);
					}
					else {
						txtbname.setText("");
						txtedition.setText("");
						txtprice.setText("");
					}
					
				}
				catch(SQLException ex){
					ex.printStackTrace();
					
				}
				
				
				
			}
		});
		textField.setColumns(10);
		textField.setBounds(116, 16, 199, 23);
		panel_1.add(textField);
	}
}
