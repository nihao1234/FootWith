package edu.thu.cslab.footwith.form;

import edu.thu.cslab.footwith.server.Mediator;
import edu.thu.cslab.footwith.server.Site;
import edu.thu.cslab.footwith.server.SiteManager;
import edu.thu.cslab.footwith.server.TextFormatException;

import org.json.JSONException;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: wangjiayu
 * Date: 13-3-16
 * Time: 下午6:00
 * To change this template use File | Settings | File Templates.
 */
public class InsertSiteForm extends JFrame {
    JTextField siteName;
    Mediator pm=new Mediator();
    public InsertSiteForm(){

        this.setTitle("增加景点");
        init();
        setBounds(100,100,400,200);
        this.setVisible(true);
    }

    private void init() {

        BorderLayout borderLayout = new BorderLayout();
        getContentPane().setLayout(borderLayout);
         borderLayout.setVgap(5);

        JPanel  topPanel = new JPanel();
        this.add(topPanel,"North");

         JPanel mainPanel = new JPanel();
         GridLayout gridLayout = new GridLayout(3,4);
         gridLayout.setHgap(5);
        gridLayout.setVgap(5);
        mainPanel.setBorder(new LineBorder(SystemColor.activeCaptionBorder,4,false));
        mainPanel.setLayout(gridLayout);


        final JLabel siteNameLabel = new JLabel("景点名称");
        siteName = new JTextField();
        mainPanel.add(siteNameLabel);
        mainPanel.add(siteName);

        final  JLabel siteRateLabel = new JLabel("景点级别");
        final  JTextField siteRate = new JTextField();
        mainPanel.add(siteRateLabel);
        mainPanel.add(siteRate);

        final  JLabel siteLocationLable = new JLabel("景点位置");
        final JTextField siteLocation =new JTextField();
        mainPanel.add(siteLocationLable);
        mainPanel.add(siteLocation);

        final JLabel siteBriefLabel = new JLabel("介绍");
        mainPanel.add(siteBriefLabel);
        final JTextField siteBrief = new JTextField();
        mainPanel.add(siteBrief);

        final JLabel sitePictureLabel = new JLabel("景点图片");
        mainPanel.add(sitePictureLabel);
        //  final Image siteImage = new Im
        final  JTextField sitePicture = new JTextField();
        mainPanel.add(sitePicture);

        getContentPane().add(mainPanel,"Center");



        final  JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(new LineBorder(SystemColor.activeCaptionBorder,1,false));
        getContentPane().add(bottomPanel,"South") ;
        final  FlowLayout flowLayout = new FlowLayout();
        flowLayout.setVgap(2);
        flowLayout.setHgap(30);
        flowLayout.setAlignment(FlowLayout.RIGHT);
        bottomPanel.setLayout(flowLayout);
        final JButton btnAdd = new JButton("增加");
        final JButton btnReset = new JButton("重置");
        bottomPanel.add(btnAdd);
        bottomPanel.add(btnReset);


         btnAdd.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent e) {
                               if(siteName.getText() == null || siteName.getText().length() == 0) {
                                   JOptionPane.showMessageDialog(null,"景点名称不能为空");
                               }else if(siteRate.getText() == null || siteRate.getText().length() == 0) {
                                   JOptionPane.showMessageDialog(null,"景点级别不能为空");
                               }else if(siteLocation.getText() == null || siteLocation.getText().length() == 0) {
                                   JOptionPane.showMessageDialog(null,"景点位置不能为空");
                               }else {
                                   Site newSite = new Site();
                                   SiteManager siteManager = new SiteManager();
                                   try {

                                       newSite.setSiteName(siteName.getText().toString().trim());
                                       newSite.setRate(new Integer(siteRate.getText().toString().trim()).intValue());
                                       newSite.setLocation(siteLocation.getText().toString().trim());
                                       //  newSite.setPicture(sitePicture.getText().toString().trim());
                                       newSite.setBrief(siteBrief.getText().toString().trim());
                                       newSite.setPicture(new Integer(sitePicture.getText().toString().trim()).intValue());
                                       try {
                                           siteManager.addSite(newSite);
                                           JOptionPane.showMessageDialog(null,"添加成功");

                                       } catch (SQLException e1) {
                                           e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                                       }
                                   } catch (TextFormatException e1) {
                                       e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                                   }


             }
             }
         });
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                siteLocation.setText("");
                siteName.setText("");
                siteRate.setText("");
                siteBrief.setText("");
                sitePicture.setText("");
            }
        });

    }


   
}
