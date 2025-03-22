package employee.management.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class ViewEmployee extends JFrame implements ActionListener {

    JTable table;

    Choice choiceEMP;

    JButton searchbtn, print, update, back;

    ViewEmployee() {

        getContentPane().setBackground(new Color(255,131,122));
        JLabel search = new JLabel("Search by employee id");
        search.setBounds(20,20,150,20);
        add(search);

        choiceEMP = new Choice();
        choiceEMP.setBounds(180,20,150,20);
        add(choiceEMP);

        try {
            Conn c = new Conn();
            ResultSet resultSet = c.statement.executeQuery("select *from employee");
            while (resultSet.next()) {
                choiceEMP.add(resultSet.getString("empID"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        table = new JTable();

        try {

            Conn c = new Conn();
            ResultSet resultSet = c.statement.executeQuery("select *from employee");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));

        } catch (Exception E) {
            E.printStackTrace();
        }

        JScrollPane jp = new JScrollPane(table);
        jp.setBounds(0,100,900,600);
        add(jp);

        searchbtn = new JButton("Search");
        searchbtn.setBounds(20,70,100,25);
        searchbtn.setForeground(Color.WHITE);
        searchbtn.setBackground(Color.BLACK);
        searchbtn.addActionListener(this);
        add(searchbtn);

        print = new JButton("Print");
        print.setBounds(130,70,100,25);
        print.setForeground(Color.WHITE);
        print.setBackground(Color.BLACK);
        print.addActionListener(this);
        add(print);

        update = new JButton("Update");
        update.setBounds(240,70,100,25);
        update.setForeground(Color.WHITE);
        update.setBackground(Color.BLACK);
        update.addActionListener(this);
        add(update);

        back = new JButton("Back");
        back.setBounds(350,70,100,25);
        back.setForeground(Color.WHITE);
        back.setBackground(Color.BLACK);
        back.addActionListener(this);
        add(back);

        setSize(900, 700);
        setLayout(null);
        setLocation(300,100);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == searchbtn) {
            String query = "select *from employee where empID = '"+choiceEMP.getSelectedItem()+"'";

            try {

                Conn c = new Conn();
                ResultSet resultSet = c.statement.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(resultSet));

            }catch (Exception E) {
                E.printStackTrace();
            }
        } else if (e.getSource() == print) {

            try {

                table.print();

            } catch (Exception E) {
                E.printStackTrace();
            }
        } else if (e.getSource()== update) {
            setVisible(false);
            new UpdateEmployee(choiceEMP.getSelectedItem());

        } else {
            setVisible(false);
            new Main_class();
        }

    }

    public static void main(String[] args) {

        new ViewEmployee();
    }
}
