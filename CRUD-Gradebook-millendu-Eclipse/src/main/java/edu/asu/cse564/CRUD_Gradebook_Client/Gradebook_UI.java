package edu.asu.cse564.CRUD_Gradebook_Client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sun.jersey.api.client.ClientResponse;


import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ButtonGroup;

public class Gradebook_UI extends JFrame {

	ClientApp clientApp = new ClientApp();
	
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	
	private int radioFlag;
	private JTextField textField_7;
	private JTextField textField_8;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gradebook_UI frame = new Gradebook_UI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Gradebook_UI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("CREATE");
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					radioFlag = 1;
        }
    });
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("READ");
		buttonGroup.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				radioFlag = 2;
		}
	});
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("UPDATE");
		buttonGroup.add(rdbtnNewRadioButton_2);
		rdbtnNewRadioButton_2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				radioFlag = 3;
		}
	});
		JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("DELETE");
		buttonGroup.add(rdbtnNewRadioButton_3);
		rdbtnNewRadioButton_3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				radioFlag = 4;
		}
	});
		
		JLabel lblStudentId = new JLabel("Student ID");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblStudentName = new JLabel("Student Name");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JLabel lblGradeId = new JLabel("Grade ID");
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		JLabel lblGrade = new JLabel("Grade");
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		JLabel lblWeightage = new JLabel("Weightage");
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		
		JLabel lblFeedback = new JLabel("Feedback");
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		
		JLabel lblStudentDetails = new JLabel("Student Details");
		
		JLabel lblGradeitemDetails = new JLabel("GradeItem Details");
		
		JLabel lblLocation = new JLabel("Location");
		
		textField_6 = new JTextField();
		textField_6.setColumns(50);
		
		JLabel lblMethod = new JLabel("Method");

		textField_7 = new JTextField();
		textField_7.setColumns(10);
		
		JLabel lblResponse = new JLabel("Response");
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);

		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if(radioFlag == 1) {
                	
                	String studentID = textField.getText();
                	String studentName = textField_1.getText();
                	
                	String gradeID = textField_2.getText();
                	String grade = textField_3.getText();
                	String weightage = textField_4.getText();
                	
                	if(!studentID.equals("") && !studentName.equals("")) {
                		ClientResponse r = clientApp.createStudent(studentID, studentName);
                		String[] tokens = r.toString().split(" "); 
                		textField_7.setText(tokens[0]);
                		textField_6.setText(tokens[1]);
                		textField_8.setText(tokens[7]);
                	}
                	
                	else {
                		if(!gradeID.equals("") && !weightage.equals("")) {
                	
	                		ClientResponse r = clientApp.createGradeItem(gradeID, weightage);
	                		String[] tokens = r.toString().split(" "); 
	                		textField_7.setText(tokens[0]);
	                		textField_6.setText(tokens[1]);
	                		textField_8.setText(tokens[7]);
	                	}
                		else {
                    		textField_6.setText(" Enter values for creating grade or student");
                    	}
                	}
                	                	
                }
                
                else if(radioFlag == 2) {
                	
                	String studentID = textField.getText();
                	
                	if(!studentID.equals("")) {
                		ClientResponse r = clientApp.getStudent(studentID);
                		String[] tokens = r.toString().split(" "); 
                		textField_7.setText(tokens[0]);
                		textField_6.setText(tokens[1]);
                		textField_8.setText(tokens[7]);
                	}
                	
                	else {
                		textField_6.setText("Enter values for getting student info");
                	}
                	
                }
                
                else if(radioFlag == 3) {
                	
                	String studentID = textField.getText();
                	
                	String gradeID = textField_2.getText();
                	String grade = textField_3.getText();
                	String feedback = textField_5.getText();
                	
                	if(!studentID.equals("") && !gradeID.equals("") && !grade.equals("") && !feedback.equals("")) {
                		ClientResponse r = clientApp.updateGrade(studentID, gradeID, grade, feedback);
                		String[] tokens = r.toString().split(" "); 
                		textField_7.setText(tokens[0]);
                		textField_6.setText(tokens[1]);
                		textField_8.setText(tokens[7]);
                	}
                	
                	else {
                		textField_6.setText("Enter values for updating student grade");
                	}
                }
                
                else if(radioFlag == 4) {
                	
                	String studentID = textField.getText();
                	
                	String gradeID = textField_2.getText();
                	
                	if(!studentID.equals("")) {
                		if(!gradeID.equals("")) {
                			ClientResponse r = clientApp.deleteGrade(studentID, gradeID);
                    		String[] tokens = r.toString().split(" "); 
                    		textField_7.setText(tokens[0]);
                    		textField_6.setText(tokens[1]);
                    		textField_8.setText(tokens[7]);
                		}
                		
                		else {
                			ClientResponse r = clientApp.deleteStudent(studentID);
                    		String[] tokens = r.toString().split(" "); 
                    		textField_7.setText(tokens[0]);
                    		textField_6.setText(tokens[1]);
                    		textField_8.setText(tokens[7]);
                		}
                	}
                	
                	else if(!gradeID.equals("")){
                		ClientResponse r = clientApp.deleteGradeItem(gradeID);
                		String[] tokens = r.toString().split(" "); 
                		textField_7.setText(tokens[0]);
                		textField_6.setText(tokens[1]);
                		textField_8.setText(tokens[7]);
                	}
                	
                	else {
                		textField_6.setText("Enter student or grade id to delete");
                	}
                	 
                }
                
                else {
                	textField_6.setText("Select an operation");
                }
            }
        });
		
		JButton btnReadGradebook = new JButton("Read Gradebook");
		btnReadGradebook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	ClientResponse r = clientApp.getGradeBook();
        		String[] tokens = r.toString().split(" "); 
        		textField_7.setText(tokens[0]);
        		textField_6.setText(tokens[1]);
        		textField_8.setText(tokens[7]);
            }
        });
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(rdbtnNewRadioButton)
											.addGap(30))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(rdbtnNewRadioButton_1)
											.addGap(45)))
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblStudentName)
										.addComponent(lblStudentId)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(rdbtnNewRadioButton_3)
									.addGap(45)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblGrade)
										.addComponent(lblGradeId)
										.addComponent(lblWeightage)
										.addComponent(lblFeedback))))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(rdbtnNewRadioButton_2)
							.addGap(62)
							.addComponent(lblGradeitemDetails)))
					.addContainerGap(99, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(181, Short.MAX_VALUE)
					.addComponent(lblStudentDetails)
					.addGap(184))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnReadGradebook)
							.addGap(170)
							.addComponent(btnSubmit))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblLocation)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblResponse)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField_8, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblMethod))))
					.addGap(45))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(344, Short.MAX_VALUE)
					.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addGap(45))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblStudentDetails)
					.addGap(7)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnNewRadioButton)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStudentId))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnNewRadioButton_1)
						.addComponent(lblStudentName)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnNewRadioButton_2)
						.addComponent(lblGradeitemDetails))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnNewRadioButton_3)
						.addComponent(lblGradeId)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGrade)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblWeightage)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFeedback)
						.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblLocation)
								.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMethod))
							.addGap(5)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblResponse)
								.addComponent(textField_8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(6)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnReadGradebook)
						.addComponent(btnSubmit)))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
}
