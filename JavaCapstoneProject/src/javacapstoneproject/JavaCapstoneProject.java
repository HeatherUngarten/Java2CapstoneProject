package JavaCapstoneProject;
import javax.swing.*; //needed for swing class
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.Scanner;


public class JavaCapstoneProject extends JFrame
{
    String gender;
    String selectedRace;
    private final int WINDOW_WIDTH = 600;
    private final int WINDOW_HEIGHT = 1000;
    private JPanel characterPanel;
    private JPanel selectedCharacterPanel;  
    private JPanel characterDetailsPanel;
    private JTextField characterName;
    private JComboBox characterBox;
    private JComboBox raceBox;
    private JLabel psLabel;    
    private JLabel prLabel;
    private JLabel characterLabel;
    private JLabel imageLabel;
    private JLabel nameLabel;
    private JLabel classLabel;
    private JLabel strengthLabel;
    private JLabel wisdomLabel;
    private JLabel intelligenceLabel;
    private JLabel dexterityLabel;
    private JTextField selectedCharacter;  
    private JTextField pointsSpent;   
    private JTextField pointsRemaining;   
    private JSlider sliderStrength;
    private JSlider sliderIntelligence;
    private JSlider sliderDexterity;
    private JSlider sliderWisdom;   
    private JRadioButton femaleButton;
    private JRadioButton maleButton;
    private ButtonGroup radioButtonGroup;
    private JButton saveButton;
    private JButton openFileButton;
    String [] characters = {"Warrior", "Wizard", "Cleric", "Theif", "Barbarian"};
    String [] races = {"Human", "Dwarf", "HighElf", "Gnome", "Orc", "Buffalauren"};

    public JavaCapstoneProject()
    {
        
        setTitle("Character Creation Program"); 
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT); 
        setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout()); 
        
        buildCharacterPanel();
        buildSelectedCharacterPanel();
        buildCharacterDetailsPanel();

        //add panels to the content pane
        add(characterPanel, BorderLayout.WEST);
        add(selectedCharacterPanel, BorderLayout.SOUTH);
        add(characterDetailsPanel, BorderLayout.CENTER);
        
        pointsSpent.setText("100");
        pointsRemaining.setText("0");
        characterBox.setSelectedItem(0);
        raceBox.setSelectedItem(0);
        gender = "female";
   
        pack();
        setVisible(true);      
    }
        
    private void buildCharacterPanel() 
    {
        characterPanel = new JPanel();
        characterPanel.setBackground(Color.WHITE);
        characterPanel.setLayout (new GridLayout (3,1));
        characterPanel.setBorder(BorderFactory.createTitledBorder("Character"));
        //characterPanel.setSize(500,500);
        imageLabel = new JLabel ("Here is Your Charcter!!!");
        characterPanel.add(imageLabel); 
        characterBox = new JComboBox(characters);
        characterBox.addActionListener(new ComboBoxListener());
        characterPanel.add(characterBox);                        
        femaleButton = new JRadioButton("Female", true);
        maleButton = new JRadioButton("Male");        
        radioButtonGroup = new ButtonGroup();
        radioButtonGroup.add(femaleButton);
        radioButtonGroup.add(maleButton);
        maleButton.addActionListener(new RadioButtonListener());
        femaleButton.addActionListener(new RadioButtonListener());       
        characterPanel.add(femaleButton);
        characterPanel.add(maleButton);
    }// end build character panel
 
    private void buildSelectedCharacterPanel() 
    {
        // Create a panel to hold components
        selectedCharacterPanel = new JPanel();
        characterLabel = new JLabel ("You selected: ");
        //characterLabel.setSize(300, 100);        
        selectedCharacter = new JTextField(50);
        selectedCharacter.setEditable(false);
        saveButton = new JButton("SAVE");
        openFileButton = new JButton("Load Character");        
        selectedCharacterPanel.add(characterLabel);
        selectedCharacterPanel.add(selectedCharacter);
        selectedCharacterPanel.add(saveButton);
        selectedCharacterPanel.add(openFileButton);
        saveButton.addActionListener(new saveButtonListener());
        openFileButton.addActionListener(new openButtonListener());
    }//end build selected panel
    
    private void buildCharacterDetailsPanel() 
    {
        characterDetailsPanel = new JPanel();
        characterDetailsPanel.setLayout(new GridLayout(8,1));
        characterDetailsPanel.setBorder(BorderFactory.createTitledBorder("Character Settings"));
        characterName = new JTextField(20);
        pointsSpent = new JTextField(3);
        pointsRemaining = new JTextField (3);
        
        //Initialize Values if user doesn't change defaults
        psLabel = new JLabel ("Points Used: ");
        prLabel = new JLabel ("Available Points: ");
        classLabel = new JLabel("Character Class:");       
        nameLabel = new JLabel ("Enter Character Name: ");  
        strengthLabel = new JLabel ("Strength: ");
        intelligenceLabel = new JLabel ("Intellegence: ");
        wisdomLabel = new JLabel ("Wisdom: ");
        dexterityLabel = new JLabel ("Dexterity: ");
        
        sliderStrength = new JSlider(JSlider.HORIZONTAL, 0, 100, 25);
        sliderStrength.setMajorTickSpacing(25);//could I make this a format and us it for all??
        sliderStrength.setMinorTickSpacing(5);
        sliderStrength.setPaintTicks(true);
        sliderStrength.setPaintLabels(true);
                
        sliderIntelligence= new JSlider(JSlider.HORIZONTAL, 0, 100, 25);
        sliderIntelligence.setMajorTickSpacing(25);//could I make this a format and us it for all??
        sliderIntelligence.setMinorTickSpacing(5);
        sliderIntelligence.setPaintTicks(true);
        sliderIntelligence.setPaintLabels(true);
        
        sliderDexterity= new JSlider(JSlider.HORIZONTAL, 0, 100, 25);
        sliderDexterity.setMajorTickSpacing(25);//could I make this a format and us it for all??
        sliderDexterity.setMinorTickSpacing(5);
        sliderDexterity.setPaintTicks(true);
        sliderDexterity.setPaintLabels(true);
        
        sliderWisdom= new JSlider(JSlider.HORIZONTAL, 0, 100, 25);
        sliderWisdom.setMajorTickSpacing(25);//could I make this a format and us it for all??
        sliderWisdom.setMinorTickSpacing(5);
        sliderWisdom.setPaintTicks(true);
        sliderWisdom.setPaintLabels(true);
 
        pointsSpent.setEditable(false);
        pointsRemaining.setEditable(false);
        characterName.setEditable(true);
      
        raceBox = new JComboBox(races);
        raceBox.addActionListener(new RaceBoxListener());
        
       
        characterDetailsPanel.add(nameLabel);
        characterDetailsPanel.add(characterName); 
        
        characterDetailsPanel.add(classLabel);
        characterDetailsPanel.add(raceBox);
        
        characterDetailsPanel.add(psLabel);
        characterDetailsPanel.add(pointsSpent);
        
        characterDetailsPanel.add(prLabel);
        characterDetailsPanel.add(pointsRemaining);
        
        characterDetailsPanel.add(strengthLabel);
        characterDetailsPanel.add(sliderStrength);
        characterDetailsPanel.add(intelligenceLabel);
        characterDetailsPanel.add(sliderIntelligence);
        characterDetailsPanel.add(wisdomLabel);
        characterDetailsPanel.add(sliderWisdom);
        characterDetailsPanel.add(dexterityLabel);
        characterDetailsPanel.add(sliderDexterity);
        
        sliderStrength.addChangeListener(new SliderListener1());
        sliderIntelligence.addChangeListener(new SliderListener2());
        sliderWisdom.addChangeListener(new SliderListener3());
        sliderDexterity.addChangeListener(new SliderListener4());
    }
    
    
    private class ComboBoxListener implements ActionListener
    {
        public void actionPerformed (ActionEvent e)
        {
            String characterClass = "null";
            String selection = (String) characterBox.getSelectedItem();
            selectedCharacter.setText(selection);
            imagePicker(selection);
            characterClass = selection;
        }
    }
    
    private class RaceBoxListener implements ActionListener
    {
        public void actionPerformed (ActionEvent e)
        {
            String selectedRace = (String)raceBox.getSelectedItem();
        }
    }
    
    public class RadioButtonListener implements ActionListener
    {
        public void actionPerformed (ActionEvent e)
        {

            if (e.getSource() == femaleButton)
            {
                gender = "female";
            }
            else if (e.getSource() == maleButton)
            {
                gender = "male";
            }
            else 
            {
                gender = "null";
            }
        }   
    }
    
   
    
    private class SliderListener1 implements ChangeListener
    {
        public void stateChanged ( ChangeEvent e)
        {
            int strength = sliderStrength.getValue();
            int intelligence = sliderIntelligence.getValue();
            int wisdom = sliderWisdom.getValue();
            int dexterity = sliderDexterity.getValue();
            
            int total = (strength + intelligence + wisdom + dexterity);
            String pointsSpnt = Integer.toString(total);
            pointsSpent.setText(pointsSpnt);
            
            int pointsAllowed = 100;
            int pointsLeft = (pointsAllowed - total);
            pointsRemaining.setText(Integer.toString(pointsLeft));   
            
            if (pointsLeft < 0)
            {
                sliderStrength.setValue(100 - (sliderIntelligence.getValue() + 
                sliderWisdom.getValue() + sliderDexterity.getValue()));
            }            
        }  
    }
    
    private class SliderListener2 implements ChangeListener
    {
        public void stateChanged ( ChangeEvent e)
        {
            int strength = sliderStrength.getValue();
            int intelligence = sliderIntelligence.getValue();
            int wisdom = sliderWisdom.getValue();
            int dexterity = sliderDexterity.getValue();
            
            int total = (strength + intelligence + wisdom + dexterity);
            String pointsSpnt = Integer.toString(total);
            pointsSpent.setText(pointsSpnt);
            
            int pointsAllowed = 100;
            int pointsLeft = (pointsAllowed - total);
            pointsRemaining.setText(Integer.toString(pointsLeft));   
 
            if (pointsLeft < 0)
            {
                sliderIntelligence.setValue(100 - (sliderStrength.getValue() + 
                sliderWisdom.getValue() + sliderDexterity.getValue()));
            }
                
        }
  
    }
    
    private class SliderListener3 implements ChangeListener
    {
        public void stateChanged ( ChangeEvent e)
        {
            int strength = sliderStrength.getValue();
            int intelligence = sliderIntelligence.getValue();
            int wisdom = sliderWisdom.getValue();
            int dexterity = sliderDexterity.getValue();
            
            int total = (strength + intelligence + wisdom + dexterity);
            String pointsSpnt = Integer.toString(total);
            pointsSpent.setText(pointsSpnt);
            
            int pointsAllowed = 100;
            int pointsLeft = (pointsAllowed - total);
            pointsRemaining.setText(Integer.toString(pointsLeft));   

            if (pointsLeft < 0)
            {
                sliderWisdom.setValue(100 - (sliderStrength.getValue() + 
                sliderIntelligence.getValue() + sliderDexterity.getValue()));
            }
                
        }
  
    }
    
    private class SliderListener4 implements ChangeListener
    {
        public void stateChanged ( ChangeEvent e)
        {
            int strength = sliderStrength.getValue();
            int intelligence = sliderIntelligence.getValue();
            int wisdom = sliderWisdom.getValue();
            int dexterity = sliderDexterity.getValue();
            
            int total = (strength + intelligence + wisdom + dexterity);
            String pointsSpnt = Integer.toString(total);
            pointsSpent.setText(pointsSpnt);
            
            int pointsAllowed = 100;
            int pointsLeft = (pointsAllowed - total);
            pointsRemaining.setText(Integer.toString(pointsLeft));               
            if (pointsLeft < 0)
            {
                sliderDexterity.setValue(100 - (sliderIntelligence.getValue() + 
                sliderStrength.getValue() + sliderWisdom.getValue()));
               
            }               
        }
    }
  
    private class saveButtonListener implements ActionListener
    {
        public void actionPerformed ( ActionEvent e)
        {
            writeFile();            
        }    
  
    }
    
    private class openButtonListener implements ActionListener
    {
        public void actionPerformed ( ActionEvent e)
        {           
            openFileChooser();   
        }//end actionPerformed  
    }//end openButtonListener
        
        
    public void writeFile()                   
    {
        String charName = characterName.getText();
        String characterClass = (String) characterBox.getSelectedItem();
        String selectedRace1 = (String)raceBox.getSelectedItem();
        String strength = Integer.toString(sliderStrength.getValue());
        String intelligence = Integer.toString(sliderIntelligence.getValue());
        String wisdom = Integer.toString(sliderWisdom.getValue());
        String dexterity = Integer.toString(sliderDexterity.getValue());     
        System.out.println(selectedRace1);
        
        try 
        {
            
            JFileChooser fc = new JFileChooser();
            int returnVal = fc.showSaveDialog(JavaCapstoneProject.this);
            if (returnVal != JFileChooser.APPROVE_OPTION) 
            {
                JOptionPane.showMessageDialog(null,"No File Selected");
            }
                 
            File file = fc.getSelectedFile(); 
            BufferedWriter out = new BufferedWriter(new FileWriter(file)); 
            out.write(charName);
            out.newLine();
            out.write(characterClass);
            out.newLine();
            out.write(gender);
            out.newLine();
            out.write(selectedRace1);
            out.newLine();
            out.write(strength);
            out.newLine();
            out.write(intelligence);
            out.newLine();
            out.write(wisdom);
            out.newLine();
            out.write(dexterity);
            out.newLine();        
            out.close();
         }

        catch (IOException e)
        {
            e.printStackTrace();
        }
    }  
    
        
    public void openFileChooser()
    {
       JFileChooser fc = new JFileChooser();
       int returnVal = fc.showOpenDialog(JavaCapstoneProject.this);
        
       if (returnVal == JFileChooser.APPROVE_OPTION) 
       {
            File file = fc.getSelectedFile();
            String inFileName = file.getName();
        
            try
        { 
          ReadFile(inFileName);
        }        

        catch(FileNotFoundException e)
        {
            JOptionPane.showMessageDialog (null, "File not found:");
            System.exit(1);
        }  
       } 
       
       else 
       {
            JOptionPane.showMessageDialog (null,"File not available");
       }
    }
      
  
    public void ReadFile(String inFileName) throws FileNotFoundException
    {
        String [] array = new String [8];    
        int i = 0;
        File file = new File (inFileName);
        Scanner inputFile = new Scanner(file);

        for (i= 0; i < 8; i++)
        {
            array [i] = inputFile.nextLine();  
        }
        
        String n = array[0];
        String cc = array[1];
        String g = array[2];
        String r = array [3];
        int s = Integer.parseInt(array [4]);
        int in = Integer.parseInt(array [5]);
        int w = Integer.parseInt(array [6]);
        int d = Integer.parseInt(array [7]);
        int num = 0;
        
        for (i= 0; i < 8; i++)
        {
            System.out.println(array [i]);  
        }
        inputFile.close();
    
        imagePicker(cc);
        
        if (g.equals("female"))
                {
                    gender = "female";
                }
        else gender = "male";
        
        
        characterBox.setSelectedIndex(imagePicker(cc));
        
        //I need to parse through race box array to find a match 
        if (r.equals ("Human"))
        {
            num = 0;
        }
        else if (r.equals ("Dwarf"))
        {
            num = 1;
        }
        if (r.equals ("HighElf"))
        {
            num = 2;
        }
        if (r.equals ("Gnome"))
        {
            num = 3;
        }
         if (r.equals ("Orc"))
        {
            num = 4;
        }
        if (r.equals ("Buffalauren"))
        {
            num = 5;
        }

        raceBox.setSelectedIndex(num); 
        characterName.setText(n);
        sliderIntelligence.setValue(in);    
        sliderWisdom.setValue(w);
        sliderDexterity.setValue(d);
        sliderStrength.setValue(s);
    }// end of read file method
    
    public static void main(String[] args) 
    {
        new JavaCapstoneProject();   
    }
    
    public int imagePicker(String selection)
    {
        String value;
        int index;
        String classtype;
        File file;
        Scanner inputFile;        

        if (selection.equals("Warrior"))
        {
            value = "Warrior.jpg";
            index = 0;
    
        }
        
        else if (selection.equals("Wizard"))
        {
            value = "Wizard.jpg";
            index = 1;
        }
        
        else if (selection.equals("Cleric"))
        {
            value = "cleric.jpg";
            index = 2;
        }

        else if (selection.equals("Theif"))
        {
            value = "theif.jpg";
            index = 3;
        }
                
        else if (selection.equals("Barbarian"))
        {
            value = "Barbarian.jpg";
            index = 4;
        }
        else 
        {
            value = "null";
            index = 0;
        }
        
        
        
        try
        {
            file = new File (value);
            inputFile = new Scanner(file);
        }    
        catch (FileNotFoundException e)
        {
            JOptionPane.showMessageDialog(null, 
                    "FileNotFound.");
        }              

        ImageIcon characterImage = new ImageIcon (value);
        imageLabel.setIcon(characterImage); 
        imageLabel.setText(null);
        return index;
    }   
}//end program
