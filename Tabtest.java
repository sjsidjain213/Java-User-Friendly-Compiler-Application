import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;

public class Tabtest extends WindowAdapter implements ActionListener,KeyListener,TextListener {
	JTabbedPane jtp;
	JFrame f;
	JTextArea jta;
    JSplitPane jsp;
    JScrollPane scrollpane;
String forrun;
    JMenuBar mb;
    JMenu file,edit,build,closetab;
    JMenuItem nw,opn,save,saveas,exit,findnextword,findandreplace,run,compile,cut,copy,paste,closeactivetab,closeall;
    
    JDialog jdaexit;
    JPanel jpexit,jpexit1;
    JButton jexitsave,jexitno,jexitcancel;
    
    JDialog jdfr;
    JPanel jpc,jpe,jps;
    JLabel findwhat,Lreplace;
    JLabel frextra1,frextra2;
    JButton findnext,replace,replaceall,cancel;
    JTextField texttofind,texttoreplace;
    
    JDialog jdf;
    JPanel jpcf,jpsf;
    JButton findnextf,cancelf;
    JLabel findwhatf;
    JLabel fextra1,fextra2;
    JTextField texttofindf;
    
    JDialog errorfind;
    JLabel lfn,lfn1,lfn2;
    JButton bfn;
    JPanel pfn;
    
String nameiswork;
    int wc=0;
    int j;
	Tabtest()
	{ 
		f = new JFrame();
		f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		f.addWindowListener(this);
		jtp = new JTabbedPane();
		jtp.setMinimumSize(new Dimension(1300,570));
		jta = new JTextArea();
		jta.setEditable(false);
		jsp = new JSplitPane(JSplitPane.VERTICAL_SPLIT,jtp,jta);
		f.setVisible(true);
		 f.setTitle("Jcreator");
		 f.add(jsp);
		 
	//	 Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		//f.setSize(screensize.width, screensize.height);
		 f.setSize(1300,500);
mb= new JMenuBar();

file= new JMenu("File");
edit= new JMenu("Edit");
build = new JMenu("Build");		
closetab = new JMenu("Close tab");
nw= new JMenuItem("New");
opn=new JMenuItem("Open");
save= new JMenuItem("Save");
saveas= new JMenuItem("SaveAs");
exit= new JMenuItem("Exit");
cut = new JMenuItem("Cut");
copy = new JMenuItem("Copy");
paste = new JMenuItem("Paste");
findnextword= new JMenuItem("Findd");
findandreplace= new JMenuItem("Find & Replace");
run = new JMenuItem("Run");
compile = new JMenuItem("Compile");
closeactivetab = new JMenuItem("Close Active Tab");
closeall = new JMenuItem("Close All Tab");

file.add(nw);
file.add(opn);
file.add(save);
file.add(saveas);
file.addSeparator();
file.add(exit);
edit.add(cut);
edit.add(copy);
edit.add(paste);
edit.add(findnextword);
edit.add(findandreplace);
build.add(compile);
build.add(run);
closetab.add(closeactivetab);
closetab.add(closeall);
mb.add(file);
mb.add(edit);
mb.add(build);
mb.add(closetab);
f.setJMenuBar(mb);

build.setEnabled(false);
closetab.setEnabled(false);
save.setEnabled(false);
saveas.setEnabled(false);
nw.addActionListener(this);
opn.addActionListener(this);
save.addActionListener(this);
saveas.addActionListener(this);
exit.addActionListener(this);
cut.addActionListener(this);
copy.addActionListener(this);
paste.addActionListener(this);
findandreplace.addActionListener(this);
findnextword.addActionListener(this);
run.addActionListener(this);
compile.addActionListener(this);
closeactivetab.addActionListener(this);
closeall.addActionListener(this);


jdfr = new JDialog();
jpc = new JPanel();
jps = new JPanel();
findwhat = new JLabel("Find What");
Lreplace = new JLabel("replace");
frextra1 = new JLabel();
frextra2 = new JLabel();
findnext = new JButton("Next");
replace = new JButton("Replace");
replaceall = new JButton("ReplaceAll");
cancel = new JButton("Cancel");
texttofind = new JTextField(20);
texttoreplace = new JTextField(20);

jdfr.setLayout(new BorderLayout());
//jdfr.setVisible(true);
jdfr.setSize(350,230);

jpc.add(findwhat);
jpc.add(texttofind);
jpc.add(frextra1);
jpc.add(frextra2);
jpc.add(Lreplace);
jpc.add(texttoreplace);
jps.add(findnext);
jps.add(replace);
jps.add(replaceall);
jps.add(cancel);
//jpe.setLayout(new GridLayout(1,4));
jpc.setLayout(new GridLayout(3,2));

jdfr.add(jpc,BorderLayout.CENTER);
jdfr.add(jps,BorderLayout.SOUTH);
//jdfr.setAlwaysOnTop(true);
texttofind.addKeyListener(this);
texttoreplace.addKeyListener(this);
findnext.addActionListener(this);
replace.addActionListener(this);
replaceall.addActionListener(this);
cancel.addActionListener(this);
jdfr.addWindowListener(new WindowAdapter()
{
	public void windowClosing(WindowEvent e)
	{
		Window w= e.getWindow();
		w.setVisible(false);
		//w.setEnabled(false);
		w.dispose();
	wc=0;
	jdfr.setEnabled(false);
	texttofind.setText("");
	texttoreplace.setText("");
	jdfr.setAlwaysOnTop(false);
	jta.select(0, 0);
	}
});
 


jdf = new JDialog();
jdf.setLayout(new BorderLayout());

jpcf = new JPanel();
jpsf = new JPanel();
jpcf.setLayout(new GridLayout(1,2));
jpsf.setLayout(new GridLayout(1,2));

findnextf = new JButton("Find.");
cancelf = new JButton(" Cancel ");
findwhatf = new JLabel("Find What");
texttofindf = new JTextField(20);

jpcf.add(findwhatf);
jpcf.add(texttofindf);
jpsf.add(findnextf);
jpsf.add(cancelf);
jdf.add(jpcf,BorderLayout.CENTER);
jdf.add(jpsf,BorderLayout.SOUTH);

findnextf.addActionListener(this);
cancelf.addActionListener(this);

jdf.addWindowListener(new WindowAdapter()
{
	public void windowClosing(WindowEvent e)
	{
		Window w= e.getWindow();
		w.setVisible(false);
		//w.setEnabled(false);
		w.dispose();
	wc=0;
	jdf.setEnabled(false);
	texttofindf.setText("");
	jdf.setAlwaysOnTop(false);
	//jta.select(0, 0);
	}
});
jdf.setSize(300, 200);

edit.setEnabled(false);
	}
	int i=0;
	
	
Vector <String>v2 = new  Vector<String>(); 
Vector <JTextArea>v = new <JTextArea>Vector(); 
HashMap<Integer,Integer> hm = new HashMap<Integer,Integer>();
LinkedHashSet <Integer> lhs = new LinkedHashSet<Integer>(); 
LinkedHashSet <Integer> lhsforsave = new LinkedHashSet<Integer>(); 
ArrayList <Integer>al = new ArrayList<Integer>();


JTextArea jta1;
public static void main(String args[])
	{
		Tabtest t = new Tabtest();
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getActionCommand().equals("New"))
		{
			int flagfound=0;
			
			JFileChooser jfcsave = new JFileChooser();
		    int option = jfcsave.showSaveDialog(f);
		    File fileee=jfcsave.getSelectedFile();
		    if(fileee==null)
			{
				return;
			}

		    String opt1=fileee.getName();
		    String opt="";
		    int opthelp = opt1.length();
		    if(opthelp>5)
		    {opt= opt1.substring(opthelp-5, opthelp);
		    }
		    else{
		    	JOptionPane.showMessageDialog(f, "Inavlid Extention Only '.java' Allowed", "JCreator", JOptionPane.PLAIN_MESSAGE);
			    
		    	return;
		    	 }
		    System.out.print(opt);
			   
		    String optj =".java";
		    if(optj.contains(opt))
		    		{System.out.println("working");
		    }
		    else{
		    	JOptionPane.showMessageDialog(f, "Inavlid Extention Only '.java' Allowed", "JCreator", JOptionPane.PLAIN_MESSAGE);
		    	return;
		    }
		    if(fileee==null)
			{
				return;
			}
		    String nameworking = fileee.getName();
		    for(int high=0;high<jtp.getTabCount();high++)
			{
			if(jtp.getTitleAt(high).equals(nameworking)){
				flagfound=1;
			}
			
			}
			
		    if(flagfound!=1)
		    {
		    	
		    
		    if (option == JFileChooser.APPROVE_OPTION) {
		        File file=jfcsave.getSelectedFile();
		String nameofnewfile = file.getName();
		if(file==null)
		{
			return;
		}
	//	int sassassastabindex=jtp.getSelectedIndex();//this is will work after jtp.addTab();
		//    v2.add(jtp.getSelectedIndex(),file.getAbsolutePath());//this is will work after jtp.addTab()
	
		
		
		v.add(i,new JTextArea());
		// v2.add(i,""); //this is is not required because it do not have any path with it and also
		//since we have v2.add(jtp.getSelectedIndex(),file.getAbsolutePath());		
		 scrollpane=new JScrollPane(v.get(i));
		jtp.addTab(nameofnewfile,scrollpane);
		jtp.setTitleAt(i, file.getName());//this is will also work after jtp.addTab()
	    v2.add(jtp.getSelectedIndex(),file.getAbsolutePath());//this is will work after jtp.addTab()
		JTextArea jtanew = v.get(i);
		jtanew.addKeyListener(this);
	//jtp.setSelectedComponent(jtanew);

		jtp.setSelectedIndex(i);
		i++;	
		j++;
		if(jtp.getTabRunCount()!=0)
		{
			saveas.setEnabled(true);			save.setEnabled(true);		edit.setEnabled(true);    build.setEnabled(true);    closetab.setEnabled(true);
		}
		else
		{
			saveas.setEnabled(false);		save.setEnabled(false);			edit.setEnabled(false);     build.setEnabled(false);			closetab.setEnabled(false);}
		}
		    
		}
		    else{
		    JOptionPane.showMessageDialog(f, "File With Same Name Already Exists "," JCreator", JOptionPane.PLAIN_MESSAGE);	
		    }
		    }
if(ae.getActionCommand().equals("Open"))
{	
	int flagfound=0;
	JFileChooser jfcopen = new JFileChooser();
    int option = jfcopen.showOpenDialog(f);
    File fileee=jfcopen.getSelectedFile();
    String opt1=fileee.getName();
    String opt="";
    int opthelp = opt1.length();
    if(opthelp>5)
    {opt= opt1.substring(opthelp-5, opthelp);
    }
    else{
    	JOptionPane.showMessageDialog(f, "Inavlid Extention Only '.java' Allowed ", "JCreator", JOptionPane.PLAIN_MESSAGE);
	    
    	return;
    	 }
    System.out.print(opt);
	   
    String optj =".java";
    if(optj.contains(opt))
    		{System.out.println("working");
    }
    else{
    	JOptionPane.showMessageDialog(f, "Inavlid Extention Only '.java' Allowed", "JCreator", JOptionPane.PLAIN_MESSAGE);
    	return;
    }
 
    if(fileee==null)
    {
    	return;
    }
    String nameworking = fileee.getName();
    nameiswork=fileee.getParent();
    for(int high=0;high<jtp.getTabCount();high++)
	{
	if(jtp.getTitleAt(high).equals(nameworking)){
		flagfound=1;
	}
	
	}
	
    if(flagfound!=1){
    System.out.println(nameworking);
    if (option == JFileChooser.APPROVE_OPTION) {
          try{
        	         File file=jfcopen.getSelectedFile();
        	         FileInputStream fis  = new FileInputStream(file);
        	         if(fileee==null)
        	         {
        	         	return;
        	         }
        	         int ch;
        	String s="";

        	while((ch=fis.read())!=-1)
        	{
        	s+=(char)ch;
        	}
        	File filee = jfcopen.getSelectedFile(); // what is the difference between them
        	File file1=new File(filee.getAbsolutePath());
		 //v2.remove(jtp.getSelectedIndex());
			String name = file.getName();
        	v.add(i,new JTextArea());
        	v2.add(i,file1.getAbsolutePath());				
    		// 	v2.add(i,new JTextArea());
    		
   		 scrollpane=new JScrollPane(v.get(i));
   			jtp.addTab(name, scrollpane);
        	JTextArea jta=v.get(i);
        jta.addKeyListener(this);

		jtp.setSelectedIndex(i);

        i++;
//f.add(jtp);
jta.setText(s);
if(jtp.getTabRunCount()!=0)
{
	edit.setEnabled(true);
	build.setEnabled(true);
	closetab.setEnabled(true);
	save.setEnabled(true);
	saveas.setEnabled(true);
}
else {
	edit.setEnabled(false);
	build.setEnabled(false);
	closetab.setEnabled(false);
	save.setEnabled(false);
	saveas.setEnabled(false);
}

        	            }
        	            catch(IOException i)
        	         { jta.setText(i.getMessage());           }
        	            
    }
}else{
    JOptionPane.showMessageDialog(f, "File With Same Name Already Exists "," JCreator", JOptionPane.PLAIN_MESSAGE);	
	   	//int q=JOptionPane.messagedialog(f,"Do you want to save "+jtp.getTitleAt(jtp.getSelectedIndex())+" ?","Save",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);	

}
    
    }
if(ae.getActionCommand().equals("Save"))
{System.out.print("enter in save");
int saveindex = jtp.getSelectedIndex();
//	System.out.println("already added");
	JTextArea ta6= v.get(jtp.getSelectedIndex());	String  d=ta6.getText();
	System.out.println((String)v2.get(jtp.getSelectedIndex()));
	File file=new File((String)v2.get(jtp.getSelectedIndex()));
	forrun = file.getAbsolutePath();
	if(file==null)
    {
    	return;
    }
	try
	{

				FileWriter fw=new FileWriter(file);		//PrintWriter pw3=new PrintWriter(file1); 
				fw.write(d);    fw.flush();			//pw3.print(d);	
				fw.close();					//pw3.close();			




	}
	catch(IOException e)
	{	jta.setText(e.getMessage());	       }

	
lhs.remove(jtp.getSelectedIndex());

    }
if(ae.getActionCommand().equals("SaveAs"))
{	JFileChooser jfcsave = new JFileChooser();
int option = jfcsave.showSaveDialog(f);
if (option == JFileChooser.APPROVE_OPTION) {
    try{
    File f=jfcsave.getSelectedFile();
    if(f==null)
    {
    	return;
    }
    FileOutputStream fos  = new FileOutputStream(f);

String store;
int ch;
int tabindex=jtp.getSelectedIndex();
JTextArea saving =v.get(tabindex);
store=saving.getText();
for(int j=0;j<store.length();j++)
{
fos.write((byte)store.charAt(j));
}
fos.close();

    }
    catch(IOException i)
    {
    	
    }}}
if(ae.getActionCommand().equals("don't save"))
{/*if(activedialog<lhs.size())
	{System.exit(1);}
else {
	ajd[activedialog].setVisible(false);
}*/
}
if(ae.getActionCommand().equals("cancel"))
{System.out.println("CHECK: enter cancel");
	jdaexit.setEnabled(false);
	jdaexit.setVisible(false);
}



if(ae.getActionCommand().equals("Compile"))
{
	String s = v.get(jtp.getSelectedIndex()).getText();
	System.out.println("**"+s+"**");
if(s.equals("")){
	
System.out.println("Enter in 1");	
}
else 
	{if(vforclose.contains(jtp.getTitleAt(jtp.getSelectedIndex()))){
	JTextArea ta6= (JTextArea)v.get(jtp.getSelectedIndex());	String  d=ta6.getText();
	
	File file=new File((String)v2.get(jtp.getSelectedIndex()));
	try
	{

				FileWriter fw=new FileWriter(file);		//PrintWriter pw3=new PrintWriter(file1); 
				fw.write(d);    fw.flush();			//pw3.print(d);	
				fw.close();					//pw3.close();			
	}
	catch(IOException e)
	{	jta.setText(e.getMessage());	       }

	String n=(String)v2.get(jtp.getSelectedIndex());
File ff=new File(n);	
n=ff.getAbsolutePath();	
if(jtp.getTabCount()==0)
	{	
		JOptionPane.showMessageDialog(f,"Please select a file!","File not found",JOptionPane.PLAIN_MESSAGE);
}
	else {
			try
			{
			Process pro=Runtime.getRuntime().exec("javac \""+n+"\"");
			pro.waitFor();
			String err="";
			int ch;
			BufferedReader br=new BufferedReader(new InputStreamReader(pro.getErrorStream()));	
				while((ch=br.read())!=-1)
				{
				err+=(char)ch;
				}
				if(err.equals(""))
				{//Program Compiled Success
					System.out.println("Leakage1");
				JOptionPane.showMessageDialog(f,"Program Compiled Successfully!","Success",JOptionPane.PLAIN_MESSAGE);


				}
				else
				{	jta.setText(err);
				JOptionPane.showMessageDialog(f,"Program Compilation Fails!","Rectify Errors",JOptionPane.PLAIN_MESSAGE);

				}
			//	jta.setText("");
			}
			catch(Exception e)
			{
				jta.setText(e.getMessage());
				jta.setText("javac : invalid flag"+n);

				JOptionPane.showMessageDialog(f,"Program Compilation Fails!","Rectify Errors ",JOptionPane.PLAIN_MESSAGE);
	}

}}
else{
	String n=(String)v2.get(jtp.getSelectedIndex());
File ff=new File(n);	
n=ff.getAbsolutePath();	
if(jtp.getTabCount()==0)
	{	
		JOptionPane.showMessageDialog(f,"Please select a file!","File not found",JOptionPane.PLAIN_MESSAGE);
}
	else {
			try
			{//exec (command,null,new file);
			Process pro=Runtime.getRuntime().exec("javac \""+n+"\"");
			pro.waitFor();
			String err="";
			int ch;
			BufferedReader br=new BufferedReader(new InputStreamReader(pro.getErrorStream()));	
				while((ch=br.read())!=-1)
				{
				err+=(char)ch;
				}
				if(err.equals(""))
				{//Program Compiled Success
System.out.println("Leakage2");
				JOptionPane.showMessageDialog(f,"Program Compiled Successfully!","Success",JOptionPane.PLAIN_MESSAGE);


				}
				else
				{
					JOptionPane.showMessageDialog(f,"Program Compilation Fails!","Rectify Errors ",JOptionPane.PLAIN_MESSAGE);

					jta.setText(err);
				}
				jta.setText("");
			}
			catch(Exception e)
			{
				jta.setText(e.getMessage());
				jta.setText("javac : invalid flag"+n);

				JOptionPane.showMessageDialog(f,"Program Compilation Fails!","Rectify Errors ",JOptionPane.PLAIN_MESSAGE);
	}
			
			System.out.println(n);
	//}
	}
	}	}
	}
if(ae.getActionCommand().equals("Run"))
{
	String n=(String)v2.get(jtp.getSelectedIndex());
File ff=new File(n);	
n=ff.getAbsolutePath();	
String nae =ff.getParent();
System.out.print(n);
	if(jtp.getTabCount()==0)
	{
		JOptionPane.showMessageDialog(f,"Please select a file!","File not found",JOptionPane.PLAIN_MESSAGE);


	}
	else
	{		
		
		String n1=(String)v2.get(jtp.getSelectedIndex());
		
		File ff1=new File(n1); 
		
File full = new File (ff1.getParent());
		n1=ff1.getParent();
		String n2=jtp.getTitleAt(jtp.getSelectedIndex());
		int ku=n2.indexOf(".java");			
		n2=n2.substring(0,ku);		System.out.println(n2);	

			try
			{
				Process pro =	Runtime.getRuntime().exec("cmd.exe /k start cmd.exe /k java -cp "+n1 ,null, full );
			String err="";
			int ch;
			
			BufferedReader br=new BufferedReader(new InputStreamReader(pro.getInputStream()));	
				while((ch=br.read())!=-1)
				{System.out.println(":::::check:::::loop");
				err+=(char)ch;
				}
		System.out.print(err);
			}
catch(Exception e)
			{
	System.out.print(e.getMessage());
			}
	

		String nine=(String)v2.get(jtp.getSelectedIndex());
System.out.println(nine);
		File f=new File(nine);	
		nine=ff.getAbsolutePath();	

			try
			{	//command+null+ file directory
System.out.println(":::::check:::::");
				
Process pro=Runtime.getRuntime().exec("cmd.exe" + null + nine);
				pro.waitFor();
				String err="";
				int ch;
				
				BufferedReader br=new BufferedReader(new InputStreamReader(pro.getInputStream()));	
					while((br.readLine()!=null))
					{System.out.println(":::::check:::::loop");
					//err+=(char)ch;
					}
			System.out.println("err is"+err);
				
			
			BufferedWriter bwr = new BufferedWriter(new OutputStreamWriter(pro.getOutputStream()));
			int chh;
			bwr.write(err);
			
			}
			
			
			catch(Exception io){
				System.out.println(io.getMessage());
			}
			}
}
if(ae.getActionCommand().equals("Cut")){ 	
	if(jtp.getTabCount()!=0)
	{
		JTextArea ta=v.get(jtp.getSelectedIndex());	
ta.cut();
	}
}

if(ae.getActionCommand().equals("Copy")){
	if(jtp.getTabCount()!=0)
	{JTextArea ta=v.get(jtp.getSelectedIndex());  ta.copy(); }

	}
if(ae.getActionCommand().equals("Paste")){
	if(jtp.getTabCount()!=0){JTextArea ta=v.get(jtp.getSelectedIndex());   ta.paste(); 
}
	}  
if(ae.getActionCommand().equals("Close Active Tab"))
{int canplaynow=0;
	for(int opo=0; opo<jtp.getTabCount();opo++)
{{if(vforclose.contains(jtp.getTitleAt(jtp.getSelectedIndex())))
		canplaynow=1;
	}
}if(canplaynow==1){
		int q=JOptionPane.showConfirmDialog(f,"Do you want to save "+jtp.getTitleAt(jtp.getSelectedIndex())+" ?","Save",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);	

if(q==JOptionPane.NO_OPTION)
{
for(int cou =0; cou<jtp.getTabCount();cou++)
{JTextArea jda ;
	  jda= v.get(cou);

	                                                                           System.out.println( "text for "+cou+"  : "+ jda.getText());	System.out.println( "text for "+cou+"  : "+ v2.get(cou));System.out.println( "Size for "+"  : "+ v2.size());	System.out.println( "Size for "+"  : "+ v.size());
}
for(int couu=0;couu<jtp.getTabCount();couu++){
	String n =(String)v2.get(couu);
	File help= new File(n);
	System.out.println("turn on inside NO "+couu+" : "+jtp.getTitleAt(jtp.getSelectedIndex()));
	System.out.println("turn on "+couu+" : "+help.getName());
	
	if(help.getName().equals(jtp.getTitleAt(jtp.getSelectedIndex()))){
		System.out.println("yeahhhh"+"  "+couu);
		System.out.println(lhs.contains(jtp.getSelectedIndex()));		
		v2.remove(couu);
		v.remove(couu);
		vforclose.remove(jtp.getTitleAt(jtp.getSelectedIndex()));

		
		i--;j--;
		jtp.remove(jtp.getSelectedIndex());
canplaynow=0;
if(jtp.getTabRunCount()!=0)
{
	saveas.setEnabled(true);			save.setEnabled(true);		edit.setEnabled(true);    build.setEnabled(true);    closetab.setEnabled(true);
}
else
{
	saveas.setEnabled(false);		save.setEnabled(false);			edit.setEnabled(false);     build.setEnabled(false);			closetab.setEnabled(false);
	}

return;
	}
}
}

if(q==JOptionPane.YES_OPTION){
	int todelete=0;
	   for(int couu=0;couu<jtp.getTabCount();couu++){
	    	String n =(String)v2.get(couu);
	    	File help= new File(n);
	    	System.out.println("turn on inside yes "+couu+" : "+jtp.getTitleAt(jtp.getSelectedIndex()));
	    	System.out.println("turn on "+couu+" : "+help.getName());
	    	
	    	if(help.getName().equals(jtp.getTitleAt(jtp.getSelectedIndex()))){
	    		System.out.println("yeahhhh"+"  "+couu);
	    	todelete=couu;
	    	}
	}
	try{int key=0;
for(int ls = 0;ls<jtp.getTabCount();ls++)
{File fill = new File(v2.get(ls));

	String name = fill.getName();
	if(name.equals(jtp.getSelectedIndex())){
		key = ls;
	}
	else {
		
	}
}
		File f=new File(v2.get(key));
   System.out.println(f.getAbsolutePath()+"  "+f.getName());  
   //f.createNewFile();
   FileOutputStream fos  = new FileOutputStream(f);
String store;	
JTextArea saving =v.get(jtp.getSelectedIndex());

store=saving.getText();        System.out.println("entered in store :  " +store);
for(int j=0;j<store.length();j++)
{
	fos.write((byte)store.charAt(j));
}
fos.close();
       }
        catch(IOException ioe)
        {      }	

    for(int couu=0;couu<jtp.getTabCount();couu++){
    	String n =(String)v2.get(couu);
    	File help= new File(n);
    	System.out.println("turn on "+ couu +" : "+jtp.getTitleAt(jtp.getSelectedIndex()));
    	System.out.println("turn on "+ couu +" : "+help.getName());
    	
    	if(help.getName().equals(jtp.getTitleAt(jtp.getSelectedIndex()))){
    		System.out.println("yeahhhh"+"  "+couu);
    		v2.remove(couu);
    		v.remove(couu);
    		vforclose.remove(jtp.getTitleAt(jtp.getSelectedIndex()));


    		    		i--;j--;
    		jtp.remove(jtp.getSelectedIndex());
    		if(jtp.getTabRunCount()!=0)
    		{
    			saveas.setEnabled(true);			save.setEnabled(true);		edit.setEnabled(true);    build.setEnabled(true);    closetab.setEnabled(true);
    		}
    		else
    		{
    			saveas.setEnabled(false);		save.setEnabled(false);			edit.setEnabled(false);     build.setEnabled(false);			closetab.setEnabled(false);
    			}

    		return;
    	}
}}
if(q==JOptionPane.CANCEL_OPTION){

return;		}

}else
{
	JTextArea t =v.get(jtp.getSelectedIndex());	
	System.out.println("size of : sdd :"+t.getSize());
	String thelp =t.getText();

	
for(int couu=0;couu<jtp.getTabCount();couu++){
	String n =(String)v2.get(couu);
	File help= new File(n);
	System.out.println("turn on inside else"+couu+" : "+jtp.getTitleAt(jtp.getSelectedIndex()));
	System.out.println("turn on "+couu+" : "+help.getName());
if(help.getName().equals(jtp.getTitleAt(jtp.getSelectedIndex()))){
		System.out.println("yeahhhh"+"  "+couu);
		v2.remove(couu);
		v.remove(couu);

		for(int opoo=0; opoo<jtp.getTabCount();opoo++)
			vforclose.remove(jtp.getTitleAt(jtp.getSelectedIndex()));
	i--;j--;
		jtp.remove(jtp.getSelectedIndex());
		if(jtp.getTabRunCount()!=0)
		{
			saveas.setEnabled(true);			save.setEnabled(true);		edit.setEnabled(true);    build.setEnabled(true);    closetab.setEnabled(true);
		}
		else
		{
			saveas.setEnabled(false);		save.setEnabled(false);			edit.setEnabled(false);     build.setEnabled(false);			closetab.setEnabled(false);
			}

		return;
	}
}}
if(jtp.getTabRunCount()!=0)
{
	saveas.setEnabled(true);			save.setEnabled(true);		edit.setEnabled(true);    build.setEnabled(true);    closetab.setEnabled(true);
}
else
{
	saveas.setEnabled(false);		save.setEnabled(false);			edit.setEnabled(false);     build.setEnabled(false);			closetab.setEnabled(false);
	}

    }

if(ae.getActionCommand().equals("Close All Tab"))
{jdaexit= new JDialog();
jpexit = new JPanel();
jpexit1 = new JPanel();

// size of j dialog insert
jexitsave = new JButton("save    ");
jexitno = new JButton("don't save    ");
jexitcancel = new JButton("cancel    ");
jexitcancel.addActionListener(this);
jexitsave.addActionListener(this);
jexitno.addActionListener(this);
jpexit.setLayout(new GridLayout(lhs.size()+1,1));
JLabel []jl = new JLabel[jtp.getTabCount()];
int has=0;
for(int z=0;z<jtp.getTabCount();z++)
{
	if(vforclose.contains(jtp.getTitleAt(z)))
	{
		jl[z]= new JLabel(jtp.getTitleAt(z));
		jpexit.add(jl[z]);
		has++;
}
	else{
			}
}		jpexit1.setLayout(new FlowLayout());
jpexit1.add(jexitsave);
jpexit1.add(jexitno);
jpexit1.add(jexitcancel);
jpexit.add(jpexit1);
jdaexit.add(jpexit);
jdaexit.setEnabled(true);
if(has!=0){

	has=0;
	jdaexit.setVisible(true);}
else{
i=0;
j=0;
	jtp.removeAll();
	v2.removeAllElements();
	v.removeAllElements();
	lhs.removeAll(lhs);
	vforclose.removeAllElements();

}
jdaexit.setSize(310,230);
if(jtp.getTabRunCount()!=0)
{System.out.print("enter in else active");
	saveas.setEnabled(true);			save.setEnabled(true);		edit.setEnabled(true);    build.setEnabled(true);    closetab.setEnabled(true);
}
else
{System.out.print("enter in else active");
	saveas.setEnabled(false);		save.setEnabled(false);			edit.setEnabled(false);     build.setEnabled(false);			closetab.setEnabled(false);
	}

}

if(ae.getActionCommand().equals("save    "))
{int move=0;

System.out.println(lhs.size());
System.out.println(vforclose.size());
	int canplaynow=0;
for(int last =0 ;last<jtp.getTabCount();last++)	
	{for(int opo=0; opo<jtp.getTabCount();opo++)
{{if(vforclose.contains(jtp.getTitleAt(opo)))
	canplaynow=1;
}
}
	
if(canplaynow==1)	{   try{
        File f= new File(v2.get(move));
        System.out.println("File: "+v2.get(move));
        FileOutputStream fos  = new FileOutputStream(f);


	String store;	
	JTextArea saving =v.get(move);
	store=saving.getText();
	System.out.println(store);
	for(int j=0;j<store.length();j++)
	{
		fos.write((byte)store.charAt(j));
	}
	fos.close();

	        }
	        catch(IOException i)
	        {
	        	
	        }	
i--;
j--;
    move++;
    }
else {
	i--;
	j--;	move++;}
}
	jtp.removeAll();
	v2.removeAllElements();
	v.removeAllElements();
	lhs.removeAll(lhs);
	vforclose.removeAllElements();
jdaexit.setEnabled(false);
jdaexit.setVisible(false);
if(jtp.getTabRunCount()!=0)
{System.out.print("enter in else active");
	saveas.setEnabled(true);			save.setEnabled(true);		edit.setEnabled(true);    build.setEnabled(true);    closetab.setEnabled(true);
}
else
{System.out.print("enter in else active");
	saveas.setEnabled(false);		save.setEnabled(false);			edit.setEnabled(false);     build.setEnabled(false);			closetab.setEnabled(false);
	}

}
if(ae.getActionCommand().equals("don't save    "))
{i=0;
j=0;
	jtp.removeAll();
	v2.removeAllElements();
	v.removeAllElements();
	lhs.removeAll(lhs);
	vforclose.removeAllElements();
jdaexit.setEnabled(false);
jdaexit.setVisible(false);
	
if(jtp.getTabRunCount()!=0)
{System.out.print("enter in  active");
	saveas.setEnabled(true);			save.setEnabled(true);		edit.setEnabled(true);    build.setEnabled(true);    closetab.setEnabled(true);
}
else
{System.out.print("enter in  active");
	saveas.setEnabled(false);		save.setEnabled(false);			edit.setEnabled(false);     build.setEnabled(false);			closetab.setEnabled(false);
	}
}
if(ae.getActionCommand().equals("cancel    "))
if(ae.getActionCommand().equals("cancel"))
{System.out.println("CHECK: enter cancel");
	jdaexit.setEnabled(false);
	jdaexit.setVisible(false);
	if(jtp.getTabRunCount()!=0)
	{
		saveas.setEnabled(true);			save.setEnabled(true);		edit.setEnabled(true);    build.setEnabled(true);    closetab.setEnabled(true);
	}
	else
	{
		saveas.setEnabled(false);		save.setEnabled(false);			edit.setEnabled(false);     build.setEnabled(false);			closetab.setEnabled(false);
		}
}

	


if(ae.getActionCommand().endsWith("Find & Replace"))
{
	jdfr.setVisible(true);
	jdfr.setEnabled(true);
jdfr.setAlwaysOnTop(true);
}
if(ae.getActionCommand().equals("Next")){
	System.out.println("enter");
	//String wordr=tf2.getText();
String wordf=texttofind.getText();
JTextArea jta = v.get(jtp.getSelectedIndex());
String Line= jta.getText();	
Pattern p = Pattern.compile(wordf); //+"//s" if we want to find exact word
Matcher m = p.matcher(Line);
if(m.find(wc))  
	{
//tf.addTextListener(this);
jta.requestFocus();
	jta.select(m.start(), m.end());
		wc=m.end();
System.out.println(wc);

}

else{
	errorfind = new JDialog(f,"Notepad");
	errorfind.setBackground(Color.WHITE);
	errorfind.setVisible(true);
	errorfind.setSize(300, 200);
	errorfind.setAlwaysOnTop(true);
	errorfind.setLayout(new BorderLayout());
	pfn= new JPanel(new FlowLayout());
	lfn1= new JLabel("");
	lfn2=new JLabel("");
	bfn= new JButton("O K");
	bfn.addActionListener(this);
	pfn.add(lfn1);
	pfn.add(bfn);
	pfn.add(lfn2);
	pfn.setBackground(Color.GRAY);
	errorfind.add(pfn,BorderLayout.SOUTH);
	lfn= new JLabel("Match not Found");
	errorfind.add(lfn,BorderLayout.CENTER);
	//errorfind.addWindowListener(this);
        }
      }
if(ae.getActionCommand().equals("Replace"))
{System.out.println("ENTER:  REPLACE");
JTextArea jta = v.get(jtp.getSelectedIndex());
System.out.println("text in area"+jta.getText());
	Pattern p=Pattern.compile(texttofind.getText()+"\\s");
	String test = jta.getText()+" ";
	Matcher m=p.matcher(test);
	System.out.println("text to replace"+texttoreplace.getText());
	System.out.println("text to Find"+texttofind.getText());
	if(m.find())
	{
		s= m.replaceFirst(texttoreplace.getText()+" ");
	//	s+=" ";
		System.out.println(s);
			
		jta.setText(s);
		
	}
	else
	{
		int x;
		int y;
		String str1;
		
		str1=jta.getSelectedText();
		x=jta.getSelectionStart();
		y=jta.getSelectionEnd();
	//	jta.(texttoreplace.getText(),x,y);
		//jta.repl

		errorfind = new JDialog(f,"Notepad");
		errorfind.setBackground(Color.WHITE);
		errorfind.setVisible(true);
		errorfind.setSize(300, 200);
		errorfind.setAlwaysOnTop(true);
		errorfind.setLayout(new BorderLayout());
		pfn= new JPanel(new FlowLayout());
		lfn1= new JLabel("");
		lfn2=new JLabel("");
		bfn= new JButton("O K");
		bfn.addActionListener(this);
		pfn.add(lfn1);
		pfn.add(bfn);
		pfn.add(lfn2);
		pfn.setBackground(Color.GRAY);
		errorfind.add(pfn,BorderLayout.SOUTH);
		lfn= new JLabel("Match not Found");
		errorfind.add(lfn,BorderLayout.CENTER);
	}

}
if(ae.getActionCommand().equals("O K"))
{
	errorfind.setVisible(false);
}
if(ae.getActionCommand().equals("Findd"))
{
	jdf.setEnabled(true);
	jdf.setVisible(true);

jdf.setAlwaysOnTop(true);
}
if(ae.getActionCommand().equals("Find."))
{
	System.out.println("enter");
	//String wordr=tf2.getText();
String wordf=texttofindf.getText();
JTextArea jta = v.get(jtp.getSelectedIndex());
System.out.println(jta.getText());
String Line= jta.getText();	
Pattern p = Pattern.compile(wordf); //+"//s" if we want to find exact word
Matcher m = p.matcher(Line);
if(m.find(wc))  
	{
//tf.addTextListener(this);
jta.requestFocus();
	jta.select(m.start(), m.end());
		wc=m.end();
System.out.println(wc);

}

else{
	errorfind = new JDialog(f,"Notepad");
	errorfind.setBackground(Color.WHITE);
	errorfind.setVisible(true);
	errorfind.setSize(300, 200);
	errorfind.setAlwaysOnTop(true);
	errorfind.setLayout(new BorderLayout());
	pfn= new JPanel(new FlowLayout());
	lfn1= new JLabel("");
	lfn2=new JLabel("");
	bfn= new JButton("O K");
	bfn.addActionListener(this);
	pfn.add(lfn1);
	pfn.add(bfn);
	pfn.add(lfn2);
	pfn.setBackground(Color.GRAY);
	errorfind.add(pfn,BorderLayout.SOUTH);
	lfn= new JLabel("Match not Found");
	errorfind.add(lfn,BorderLayout.CENTER);
}	
}
if(ae.getActionCommand().equals(" Cancel "))
		{
	System.out.println("in space");

	wc=0;
	jdf.setVisible(false);
	jdf.setEnabled(false);
	texttofindf.setText("");
	jdf.setAlwaysOnTop(false);
	//jta.select(0, 0);
		}
if(ae.getActionCommand().endsWith("Cancel"))
{
	wc=0;
	jdfr.setVisible(false);
	jdfr.setEnabled(false);
	texttofind.setText("");
	texttoreplace.setText("");
	jdfr.setAlwaysOnTop(false);
	jta.select(0, 0);
	
}
if(ae.getActionCommand().equals("Exit"))
{//System.out.println(v2.get(0));System.out.println(v2.get(1));System.out.println(v2.get(2));System.out.println(v2.get(3));

	int i=jtp.getTabCount();
System.out.println(jtp.getTabCount());
int coi=0;	
while(jtp.getTabCount()!=0){

	int ii=0;
if(lhs.contains(coi))
{

		int q=JOptionPane.showConfirmDialog(f,"Do you want to save "+jtp.getTitleAt(ii)+" ?","Save",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);	
	if(q==JOptionPane.CANCEL_OPTION){

		jtp.remove(ii);
//		v.remove(ii);
	//	v2.remove(ii);
	//	lhs.remove(coi);
//		System.out.println(v2.get(0));System.out.println(v2.get(1));System.out.println(v2.get(2));//System.out.println(v2.get(3));
		coi++;		
	}
if(q==JOptionPane.YES_OPTION){
    try{	File f=new File(jtp.getTitleAt(0));
   System.out.println(f.getAbsolutePath()+"  "+f.getName());  
   //f.createNewFile();
   FileOutputStream fos  = new FileOutputStream(f);
String store;	
JTextArea saving =v.get(coi);
store=saving.getText();        System.out.println(store);
for(int j=0;j<store.length();j++)
{
	fos.write((byte)store.charAt(j));
}
fos.close();
       }
        catch(IOException ioe)
        {      }	

	jtp.remove(ii);
	//v.remove(ii);
//	v2.remove(ii);
	//lhs.remove(coi);
	coi++;		
}
if(q==JOptionPane.NO_OPTION){

	jtp.remove(ii);
//	v.remove(ii);
//	v2.remove(ii);
	//lhs.remove(coi);
//	System.out.println(v2.get(0));System.out.println(v2.get(1));System.out.println(v2.get(2));//System.out.println(v2.get(3));
	coi++;		
	
}
}

else {
	jtp.remove(ii);
//	v.remove(ii);
//	v2.remove(ii);
	
this.i--;
coi++;
}
}

System.exit(1);}
if(ae.getActionCommand().equals("save   "))
{
for(int x=1;x<=lhs.size();x++)
	{
        try{
        	String name =activestring[dialoges.get(helpfordialog)];
    	File f=new File((String)v2.get(jtp.getSelectedIndex()));
        FileOutputStream fos  = new FileOutputStream(f);
	String store;	
	JTextArea saving =v.get(al.get(x-1));
	store=saving.getText();
	for(int j=0;j<store.length();j++)
	{
		fos.write((byte)store.charAt(j));
	}
	fos.close();

	        }
	        catch(IOException i)
	        {
	        	
	        	
	        }	
//ajd[dialoges.get(helpfordialog)].setVisible(false);
	}	
System.out.println("\n"+"  INSIDE SAVE :: value of helpfordialog  "+helpfordialog);
helpfordialog-=1;
System.out.println("\n"+"  INSIDE SAVE :: value of helpfordialog  "+helpfordialog);
System.out.println("size"+lhs.size());
if(lhs.contains(dialoges.get(helpfordialog)))
{
//ajd[dialoges.get(helpfordialog)].setVisible(true);
}
else{
	jtp.removeAll();
	v.removeAllElements();
	v2.removeAllElements();
	lhs.removeAll(lhs);
	hm.clear();
	i=0;
	j=0;
	helpfordialog=0;
	closeallcheck=0;
	System.out.println(v.size()+v2.size()+lhs.size()+hm.size());
	save.setEnabled(false);  saveas.setEnabled(false);	edit.setEnabled(false);     build.setEnabled(false);			closetab.setEnabled(false);


	//System.exit(1);
	}
//ajd[activedialog].setVisible(true);

}
if(ae.getActionCommand().equals("don't save   "))
		{
/*	activedialog+=1;

System.out.print("enter in ds");
if(activedialog<lhs.size())
{
	ajd[activedialog-1].setVisible(false);
	
}
else{
	System.exit(1);
}
ajd[activedialog].setVisible(true);

	
	*/	}
if(ae.getActionCommand().equals("cancel   "))
{/*
	activedialog+=1;

	System.out.print("enter in ds");
	if(activedialog<lhs.size())
	{
		ajd[activedialog-1].setVisible(false);
		
	}
	else{
		System.exit(1);
	}
	ajd[activedialog].setVisible(true);
*/}
}

	String match,s,activestring[];	
	int helpfordialog;
	int closeallcheck;
	int closeactivecheck;
	HashMap<Integer,Integer> dialoges = new HashMap<Integer,Integer>();
	Vector <String> vforclose =new Vector<String>();
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		s="";
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		System.out.print("CHECK : Key typped enter");
		int  active = jtp.getSelectedIndex();
		System.out.println("lhs active"+active);
	lhs.add(active);
	if(vforclose.contains(jtp.getTitleAt(active)))
	{	}
	else{vforclose.add(jtp.getTitleAt(active));
	}
	System.out.println("lhs starts from index "+lhs.contains(0));
	System.out.println("lhs size"+lhs.size());
	 if(hm.containsKey(active))
	 {
		 
	 }
	 else {
		 { al.add(active);
			 hm.put(active, active);
			 }
	 System.out.println("hm"+hm.size());
	 }	
	}
	public void windowClosing(WindowEvent we)
	{
		int i=jtp.getTabCount();
		System.out.println(jtp.getTabCount());
		int coi=0;	
		while(jtp.getTabCount()!=0){

			int ii=0;
		if(lhs.contains(coi))
		{

				int q=JOptionPane.showConfirmDialog(f,"Do you want to save "+jtp.getTitleAt(ii)+" ?","Save",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);	
			if(q==JOptionPane.CANCEL_OPTION){

				return;
//				v.remove(ii);
			//	v2.remove(ii);
			//	lhs.remove(coi);
//				System.out.println(v2.get(0));System.out.println(v2.get(1));System.out.println(v2.get(2));//System.out.println(v2.get(3));
			}
		if(q==JOptionPane.YES_OPTION){
		    try{	File f=new File(jtp.getTitleAt(0));
		   System.out.println(f.getAbsolutePath()+"  "+f.getName());  
		   //f.createNewFile();
		   FileOutputStream fos  = new FileOutputStream(f);
		String store;	
		JTextArea saving =v.get(coi);
		store=saving.getText();        System.out.println(store);
		for(int j=0;j<store.length();j++)
		{
			fos.write((byte)store.charAt(j));
		}
		fos.close();
		       }
		        catch(IOException ioe)
		        {      }	

			jtp.remove(ii);
			//v.remove(ii);
//			v2.remove(ii);
			//lhs.remove(coi);
			coi++;		
		}
		if(q==JOptionPane.NO_OPTION){

			jtp.remove(ii);
//			v.remove(ii);
//			v2.remove(ii);
			//lhs.remove(coi);
//			System.out.println(v2.get(0));System.out.println(v2.get(1));System.out.println(v2.get(2));//System.out.println(v2.get(3));
			coi++;		
			
		}
		}

		else {
			jtp.remove(ii);
//			v.remove(ii);
//			v2.remove(ii);
			
		this.i--;
		coi++;
		}
		}

		System.exit(1);
	}
	@Override
	public void textValueChanged(TextEvent arg0) {
	
	}
}
