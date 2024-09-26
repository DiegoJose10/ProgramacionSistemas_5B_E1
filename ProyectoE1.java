//Proyecto E1 Programacion Sistemas 5B 2024
//HECHO POR ANGEL EMMANUEL HERNANDEZ MARCIAL, ARMANDO LOPEZ MENCHACA Y DIEGO JOSE FLORES HERNANDEZ
//Valores de Ejemplos
//14AE47E17A14AEB905C0 DT
//5C8FC2F5287C5840 DQ
//AE474642 DD
//6455 DW
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//Diseño
public class ProyectoE1 extends JFrame implements ActionListener {
  
  private JLabel etiqueta;
  private JTextField baseField, baseField2, baseField3, baseField4, AreaField, AreaField2, AreaField3, AreaField4;
  private JButton AreaButton, AreaButton2, AreaButton3, AreaButton4 ;

  public static void main(String[] args) {
    ProyectoE1 frame = new ProyectoE1();
    frame.setSize(750, 400);
    frame.createGUI();
    frame.setVisible(true);
  }

  private void createGUI() {
  	
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    Container window = getContentPane();
    window.setLayout(new FlowLayout());
    
    etiqueta = new JLabel("PROGRAMA DE CONVERSIONES DE VALORES HEXADECIMALES EN FORMATO IEEE754 A VALORES DECIMALES");
    window.add(etiqueta);
    etiqueta = new JLabel("                HECHO POR TEAM DINAMITA: ANGEL HERNANDEZ, ARMANDO LOPEZ Y DIEGO FLORES                     ");
    window.add(etiqueta);
        
    etiqueta = new JLabel("                 Ingresa valor de 16 bits:");
    window.add(etiqueta);
    baseField = new JTextField(4);
    window.add(baseField);
    AreaButton = new JButton("Calcular decimal");
    window.add(AreaButton);
    AreaButton.addActionListener(this);
    etiqueta = new JLabel(" El decimal es:");
    window.add(etiqueta);
    AreaField = new JTextField(16);
    window.add(AreaField);
    
    etiqueta = new JLabel("                 Ingresa valor de 32 bits:");
    window.add(etiqueta);
    baseField2 = new JTextField(8);
    window.add(baseField2);
    AreaButton2 = new JButton("Calcular decimal");
    window.add(AreaButton2);
    AreaButton2.addActionListener(this);
    etiqueta = new JLabel(" El decimal es:");
    window.add(etiqueta);
    AreaField2 = new JTextField(12);
    window.add(AreaField2);
    
    etiqueta = new JLabel("  Ingresa valor de 64 bits:");
    window.add(etiqueta);
    baseField3 = new JTextField(16);
    window.add(baseField3);
    AreaButton3 = new JButton("Calcular decimal");
    window.add(AreaButton3);
    AreaButton3.addActionListener(this);
    etiqueta = new JLabel(" El decimal es:");
    window.add(etiqueta);
    AreaField3 = new JTextField(12);
    window.add(AreaField3);
    
    etiqueta = new JLabel("Ingresa valor de 80 bits:");
    window.add(etiqueta);
    baseField4 = new JTextField(20);
    window.add(baseField4);
    AreaButton4 = new JButton("Calcular decimal");
    window.add(AreaButton4);
    AreaButton4.addActionListener(this);
    etiqueta = new JLabel(" El decimal es:");
    window.add(etiqueta);
    AreaField4 = new JTextField(12);
    window.add(AreaField4);
  }
  //Bases y areas de los textos
  public void actionPerformed(ActionEvent event) {
  	JButton source = (JButton)event.getSource(); 

    if (AreaButton == source  ){
    	String base = (baseField.getText());
    	AreaField.setText(Double.toString(ConvertirDieciseis(base)));
    }
    
    if (AreaButton2 == source  ){
    	String base2 = (baseField2.getText());
    	AreaField2.setText(Double.toString(ConvertirTreintaDos(base2)));
    }
    
    if (AreaButton3 == source  ){
    String base3 = (baseField3.getText());
    AreaField3.setText(Double.toString(ConvertirSesentaCuatro(base3)));
    }
    
    if (AreaButton4 == source  ){
    String base4 = (baseField4.getText());
    AreaField4.setText(Double.toString(ConvertirOchenta(base4)));
    }
  }
 
  //Funcion para DW
  Double ConvertirDieciseis(String valorHexadecimal){
  	//Convertir de little endian a big endian
  	String Hex="0";
  	for(int i=0;i<=1;i++){
  		if(i==0){
  		Hex = Character.toString(valorHexadecimal.charAt(i+2));
  		}else{
  			Hex = Hex + Character.toString(valorHexadecimal.charAt(i+2));
  		}
  	}
  	for(int i=2;i<=3;i++){
  		Hex = Hex + Character.toString(valorHexadecimal.charAt(i-2));
  	}
  	//Convertir a binario
  	String Hexa="0000";
  	for(int i=0;i<=3;i++){
  		if(i==0){
  			Hexa = ConvertirHexBin(Hex.charAt(i));
  		}else{
  			Hexa = Hexa + ConvertirHexBin(Hex.charAt(i));
  		}
  	}
  	//Signo
  	String signo="00";
  	for(int i=0;i<1;i++){
  			if(Hexa.charAt(i)=='0'){
  				signo = "pp";
  			}else{
  				signo="nn";
  			}
  	}
  	//Exponente
  	String exp="00000";
  	for(int i=1;i<6;i++){
  		if(i==1){
  		exp = Character.toString(Hexa.charAt(i));
  		}else{
  			exp = exp + Character.toString(Hexa.charAt(i));
  		}
  	}
  	//Mantisa
  	String mantisa="1";
  	for(int i=6;i<16;i++){
  		mantisa = mantisa + Character.toString(Hexa.charAt(i));
  	}
  	//Numero de exponente
  	int numExp=Integer.parseInt(exp,2); 
  	//Resta de exponente
  	int resta = numExp - 15;
  	//Parte entera
  	String ParEnt="0";
  	for(int i=0;i<=resta;i++){
  		if(i==0){
  			ParEnt = Character.toString(mantisa.charAt(i));
  		}else{
  			ParEnt = ParEnt + Character.toString(mantisa.charAt(i));
  		}
  	}
  	int partEnt=Integer.parseInt(ParEnt,2);
  	//Parte fraccionaria
  	double  ParFra=0;
  	int j=0;
  	for(int i=resta+1;i<11;i++){
  		if(mantisa.charAt(i)=='1' && j==0){
  				ParFra = ParFra + 0.5;
  		}
  		if(mantisa.charAt(i)=='1' && j==1){
  				ParFra = ParFra + 0.25;
  		}
  		if(mantisa.charAt(i)=='1' && j==2){
  				ParFra = ParFra + 0.125;
  		}
  		if(mantisa.charAt(i)=='1' && j==3){
  				ParFra = ParFra + 0.0625;
  		}
  		if(mantisa.charAt(i)=='1' && j==4){
  				ParFra = ParFra + 0.03125;
  		}
  		if(mantisa.charAt(i)=='1' && j==5){
  				ParFra = ParFra + 0.015625;
  		}
  		if(mantisa.charAt(i)=='1' && j==6){
  				ParFra = ParFra + 0.0078125;
  		}
  		if(mantisa.charAt(i)=='1' && j==7){
  				ParFra = ParFra + 0.00390625;
  		}
  		j++;
  	} 
  	//Numero Decimal Final}
  	Double numFinal = partEnt + ParFra;
  	if(signo=="pp"){
  		return numFinal;
  	}else{
  		return numFinal*-1;
  	}
}
  //Funcion para DD 
    Double ConvertirTreintaDos(String valorHexadecimal){
  	//Convertir de little endian a big endian
  	String Hex="0";
  	for(int i=0;i<=1;i++){
  		if(i==0){
  		Hex = Character.toString(valorHexadecimal.charAt(i+6));
  		}else{
  			Hex = Hex + Character.toString(valorHexadecimal.charAt(i+6));
  		}
  	}
  	for(int i=2;i<=3;i++){
  		Hex = Hex + Character.toString(valorHexadecimal.charAt(i+2));
  	}
  	for(int i=4;i<=5;i++){
  		Hex = Hex + Character.toString(valorHexadecimal.charAt(i-2));
  	}
  	for(int i=6;i<=7;i++){
  		Hex = Hex + Character.toString(valorHexadecimal.charAt(i-6));
  	}
  	
  	//Convertir a binario
  	String Hexa="0000";
  	for(int i=0;i<=7;i++){
  		if(i==0){
  			Hexa = ConvertirHexBin(Hex.charAt(i));
  		}else{
  			Hexa = Hexa + ConvertirHexBin(Hex.charAt(i));
  		}
  	}
  	//Signo
  	String signo="00";
  	for(int i=0;i<1;i++){
  			if(Hexa.charAt(i)=='0'){
  				signo = "pp";
  			}else{
  				signo="nn";
  			}
  	}
  	//Exponente
  	String exp="00000000";
  	for(int i=1;i<9;i++){
  		if(i==1){
  		exp = Character.toString(Hexa.charAt(i));
  		}else{
  			exp = exp + Character.toString(Hexa.charAt(i));
  		}
  	}
  	//Mantisa
  	String mantisa="1";
  	for(int i=9;i<32;i++){
  		mantisa = mantisa + Character.toString(Hexa.charAt(i));
  	}
  	//Numero de exponente
  	int numExp=Integer.parseInt(exp,2); 
  	//Resta de exponente
  	int resta = numExp - 127;
  	//Parte entera
  	String ParEnt="0";
  	for(int i=0;i<=resta;i++){
  		if(i==0){
  			ParEnt = Character.toString(mantisa.charAt(i));
  		}else{
  			ParEnt = ParEnt + Character.toString(mantisa.charAt(i));
  		}
  	}
  	int partEnt=Integer.parseInt(ParEnt,2);
  	//Parte fraccionaria
  	double  ParFra=0;
  	int j=0;
  	for(int i=resta+1;i<24;i++){
  		if(mantisa.charAt(i)=='1' && j==0){
  				ParFra = ParFra + 0.5;
  		}
  		if(mantisa.charAt(i)=='1' && j==1){
  				ParFra = ParFra + 0.25;
  		}
  		if(mantisa.charAt(i)=='1' && j==2){
  				ParFra = ParFra + 0.125;
  		}
  		if(mantisa.charAt(i)=='1' && j==3){
  				ParFra = ParFra + 0.0625;
  		}
  		if(mantisa.charAt(i)=='1' && j==4){
  				ParFra = ParFra + 0.03125;
  		}
  		if(mantisa.charAt(i)=='1' && j==5){
  				ParFra = ParFra + 0.015625;
  		}
  		if(mantisa.charAt(i)=='1' && j==6){
  				ParFra = ParFra + 0.0078125;
  		}
  		if(mantisa.charAt(i)=='1' && j==7){
  				ParFra = ParFra + 0.00390625;
  		}
  		j++;
  	} 
  	
  	//Numero Decimal Final
  	Double numFinal = partEnt + ParFra;
  	if(signo=="pp"){
  		return numFinal;
  	}else{
  		return numFinal*-1;
  	}
}
  //Funcion para DQ
  Double   ConvertirSesentaCuatro(String valorHexadecimal){
  	//Convertir de little endian a big endian
  	String Hex="0";
  	for(int i=0;i<=1;i++){
  		if(i==0){
  		Hex = Character.toString(valorHexadecimal.charAt(i+14));
  		}else{
  			Hex = Hex + Character.toString(valorHexadecimal.charAt(i+14));
  		}
  	}
  	for(int i=2;i<=3;i++){
  		Hex = Hex + Character.toString(valorHexadecimal.charAt(i+10));
  	}
  	for(int i=4;i<=5;i++){
  		Hex = Hex + Character.toString(valorHexadecimal.charAt(i+6));
  	}
  	for(int i=6;i<=7;i++){
  		Hex = Hex + Character.toString(valorHexadecimal.charAt(i+2));
  	}
  	for(int i=8;i<=9;i++){
  		Hex = Hex + Character.toString(valorHexadecimal.charAt(i-2));
  	}
  	for(int i=10;i<=11;i++){
  		Hex = Hex + Character.toString(valorHexadecimal.charAt(i-6));
  	}
  	for(int i=12;i<=13;i++){
  		Hex = Hex + Character.toString(valorHexadecimal.charAt(i-10));
  	}
  	for(int i=14;i<=15;i++){
  		Hex = Hex + Character.toString(valorHexadecimal.charAt(i-14));
  	}
  	//Convertir a binario
  	String Hexa="0000";
  	for(int i=0;i<=15;i++){
  		if(i==0){
  			Hexa = ConvertirHexBin(Hex.charAt(i));
  		}else{
  			Hexa = Hexa + ConvertirHexBin(Hex.charAt(i));
  		}
  	}
  	//Signo
  	String signo="00";
  	for(int i=0;i<1;i++){
  			if(Hexa.charAt(i)=='0'){
  				signo = "pp";
  			}else{
  				signo="nn";
  			}
  	}
  	//Exponente
  	String exp="00000000";
  	for(int i=1;i<12;i++){
  		if(i==1){
  		exp = Character.toString(Hexa.charAt(i));
  		}else{
  			exp = exp + Character.toString(Hexa.charAt(i));
  		}
  	}
  	//Mantisa
  	String mantisa="1";
  	for(int i=12;i<64;i++){
  		mantisa = mantisa + Character.toString(Hexa.charAt(i));
  	}
  	//Numero de exponente
  	int numExp=Integer.parseInt(exp,2); 
  	//Resta de exponente
  	int resta = numExp - 1023;
  	//Parte entera
  	String ParEnt="0";
  	for(int i=0;i<=resta;i++){
  		if(i==0){
  			ParEnt = Character.toString(mantisa.charAt(i));
  		}else{
  			ParEnt = ParEnt + Character.toString(mantisa.charAt(i));
  		}
  	}
  	int partEnt=Integer.parseInt(ParEnt,2);
  	//Parte fraccionaria
  	double  ParFra=0;
  	int j=0;
  	for(int i=resta+1;i<53;i++){
  		if(mantisa.charAt(i)=='1' && j==0){
  				ParFra = ParFra + 0.5;
  		}
  		if(mantisa.charAt(i)=='1' && j==1){
  				ParFra = ParFra + 0.25;
  		}
  		if(mantisa.charAt(i)=='1' && j==2){
  				ParFra = ParFra + 0.125;
  		}
  		if(mantisa.charAt(i)=='1' && j==3){
  				ParFra = ParFra + 0.0625;
  		}
  		if(mantisa.charAt(i)=='1' && j==4){
  				ParFra = ParFra + 0.03125;
  		}
  		if(mantisa.charAt(i)=='1' && j==5){
  				ParFra = ParFra + 0.015625;
  		}
  		if(mantisa.charAt(i)=='1' && j==6){
  				ParFra = ParFra + 0.0078125;
  		}
  		if(mantisa.charAt(i)=='1' && j==7){
  				ParFra = ParFra + 0.00390625;
  		}
  		j++;
  	} 
  	
  	//Numero Decimal Final
  	Double numFinal = partEnt + ParFra;
  	if(signo=="pp"){
  		return numFinal;
  	}else{
  		return numFinal*-1;
  	}
}
   //Funcion para DT
    Double ConvertirOchenta(String valorHexadecimal){
  	//Convertir de little endian a big endian
  	String Hex="0";
  	for(int i=0;i<=1;i++){
  		if(i==0){
  		Hex = Character.toString(valorHexadecimal.charAt(i+18));
  		}else{
  			Hex = Hex + Character.toString(valorHexadecimal.charAt(i+18));
  		}
  	}
  	for(int i=2;i<=3;i++){
  		Hex = Hex + Character.toString(valorHexadecimal.charAt(i+14));
  	}
  	for(int i=4;i<=5;i++){
  		Hex = Hex + Character.toString(valorHexadecimal.charAt(i+10));
  	}
  	for(int i=6;i<=7;i++){
  		Hex = Hex + Character.toString(valorHexadecimal.charAt(i+6));
  	}
  	for(int i=8;i<=9;i++){
  		Hex = Hex + Character.toString(valorHexadecimal.charAt(i+2));
  	}
  	for(int i=10;i<=11;i++){
  		Hex = Hex + Character.toString(valorHexadecimal.charAt(i-2));
  	}
  	for(int i=12;i<=13;i++){
  		Hex = Hex + Character.toString(valorHexadecimal.charAt(i-6));
  	}
  	for(int i=14;i<=15;i++){
  		Hex = Hex + Character.toString(valorHexadecimal.charAt(i-10));
  	}
  	for(int i=16;i<=17;i++){
  		Hex = Hex + Character.toString(valorHexadecimal.charAt(i-14));
  	}
  	for(int i=18;i<=19;i++){
  		Hex = Hex + Character.toString(valorHexadecimal.charAt(i-18));
  	}  
  	
 	//Convertir a binario
  	String Hexa="0000";
  	for(int i=0;i<=19;i++){
  		if(i==0){
  			Hexa = ConvertirHexBin(Hex.charAt(i));
  		}else{
  			Hexa = Hexa + ConvertirHexBin(Hex.charAt(i));
  		}
  	}
  	//Signo
  	String signo="00";
  	for(int i=0;i<1;i++){
  			if(Hexa.charAt(i)=='0'){
  				signo = "pp";
  			}else{
  				signo="nn";
  			}
  	}
  	//Exponente
  	String exp="00000000";
  	for(int i=1;i<16;i++){
  		if(i==1){
  		exp = Character.toString(Hexa.charAt(i));
  		}else{
  			exp = exp + Character.toString(Hexa.charAt(i));
  		}
  	}
  	//Mantisa
  	String mantisa="0";
  	for(int i=16;i<79;i++){
  		if(i==16){
  		mantisa = Character.toString(Hexa.charAt(i));	
  		}else{
  			mantisa = mantisa + Character.toString(Hexa.charAt(i));
  		}
  	}
  	
  	//Numero de exponente
  	int numExp=Integer.parseInt(exp,2); 
  	//Resta de exponente
  	int resta = numExp - 16383;
  	//Parte entera
  	String ParEnt="0";
  	for(int i=0;i<=resta;i++){
  		if(i==0){
  			ParEnt = Character.toString(mantisa.charAt(i));
  		}else{
  			ParEnt = ParEnt + Character.toString(mantisa.charAt(i));
  		}
  	}
  	int partEnt=Integer.parseInt(ParEnt,2);
  	
  	//Parte fraccionaria
  	double  ParFra=0;
  	int j=0;
  	for(int i=resta+1;i<60;i++){
  		if(mantisa.charAt(i)=='1' && j==0){
  				ParFra = ParFra + 0.5;
  		}
  		if(mantisa.charAt(i)=='1' && j==1){
  				ParFra = ParFra + 0.25;
  		}
  		if(mantisa.charAt(i)=='1' && j==2){
  				ParFra = ParFra + 0.125;
  		}
  		if(mantisa.charAt(i)=='1' && j==3){
  				ParFra = ParFra + 0.0625;
  		}
  		if(mantisa.charAt(i)=='1' && j==4){
  				ParFra = ParFra + 0.03125;
  		}
  		if(mantisa.charAt(i)=='1' && j==5){
  				ParFra = ParFra + 0.015625;
  		}
  		if(mantisa.charAt(i)=='1' && j==6){
  				ParFra = ParFra + 0.0078125;
  		}
  		if(mantisa.charAt(i)=='1' && j==7){
  				ParFra = ParFra + 0.00390625;
  		}
  		j++;
  	} 
  	//Numero Decimal Final
  	Double numFinal = partEnt + ParFra;
  	if(signo=="pp"){
  		return numFinal;
  	}else{
  		return numFinal*-1;
  	}
}
    //Conversion de hexadecimal a binario
    String ConvertirHexBin(char valorHexadecimal){
  String bin = "0000";
  	if(valorHexadecimal=='0'){ bin="0000"; }
  	if(valorHexadecimal=='1'){ bin="0001"; }
  	if(valorHexadecimal=='2'){ bin="0010"; }
  	if(valorHexadecimal=='3'){ bin="0011"; }
  	if(valorHexadecimal=='4'){ bin="0100"; }
  	if(valorHexadecimal=='5'){ bin="0101"; }
  	if(valorHexadecimal=='6'){ bin="0110"; }
  	if(valorHexadecimal=='7'){ bin="0111"; }
  	if(valorHexadecimal=='8'){ bin="1000"; }
  	if(valorHexadecimal=='9'){ bin="1001"; }
  	if(valorHexadecimal=='A'){ bin="1010"; }
  	if(valorHexadecimal=='B'){ bin="1011"; }
  	if(valorHexadecimal=='C'){ bin="1100"; }
  	if(valorHexadecimal=='D'){ bin="1101"; }
  	if(valorHexadecimal=='E'){ bin="1110"; }
  	if(valorHexadecimal=='F'){ bin="1111"; }
	return bin;
}
   //Conversion de binario a hexadecimal
   int ConvertirBinHex(String bin){
  	int valorHexadecimal=0;
  	if(bin=="0000") {valorHexadecimal=0;}
  	if(bin=="0001") {valorHexadecimal=1;}
  	if(bin=="0010") {valorHexadecimal=2;}
  	if(bin=="0011") {valorHexadecimal=3;}
  	if(bin=="0100") {valorHexadecimal=4;}
  	if(bin=="0101") {valorHexadecimal=5;}
  	if(bin=="0110") {valorHexadecimal=6;}
  	if(bin=="0111") {valorHexadecimal=7;}
  	if(bin=="1000") {valorHexadecimal=8;}
  	if(bin=="1001") {valorHexadecimal=9;}
  	if(bin=="1010") {valorHexadecimal=10;}
  	if(bin=="1011") {valorHexadecimal=11;}
  	if(bin=="1100") {valorHexadecimal=12;}
  	if(bin=="1101") {valorHexadecimal=13;}
  	if(bin=="1110") {valorHexadecimal=14;}
  	if(bin=="1111") {valorHexadecimal=15;}
	return valorHexadecimal;
	}

}