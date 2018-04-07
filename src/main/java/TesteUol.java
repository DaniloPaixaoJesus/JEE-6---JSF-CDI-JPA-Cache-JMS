
public class TesteUol {

	
	public static void main(String[] args) {
		int days = bestprogrammer(4, 5, 20);
		System.out.println("days=="+days+"==");
		
		String skills = "pcmbzpcmbzpcmbzpcmb";
		System.out.println("differentTeams=="+differentTeams(skills)+"==");
	}
	
	static int differentTeams(String skills) {
        //subjects
        /*
        p
        c
        m
        b
        z
        there are n student enrolled
        each student skilled only one
        */
        int somasSubjects[]= new int [5];
        int qtdP = 0;
        int qtdC = 0;
        int qtdM = 0;
        int qtdB = 0;
        int qtdZ = 0;
        for (char c : skills.toCharArray()) {
            if(c == 'p'){
                qtdP++;
            }
            if(c == 'c'){
                qtdC++;
            }
            if(c == 'm'){
                qtdM++;
            }
            if(c == 'b'){
                qtdB++;
            }
            if(c == 'z'){
                qtdZ++;
            }
        }
        somasSubjects[0] = qtdP;
        somasSubjects[1] = qtdC;
        somasSubjects[2] = qtdM;
        somasSubjects[3] = qtdB;
        somasSubjects[4] = qtdZ;
        int menor = somasSubjects[0];
        if(qtdP > 0
          && qtdC > 0
          && qtdM > 0
          && qtdB > 0
          && qtdZ > 0){
            //buscar menor numero;
            for(int i = 0; i<= somasSubjects.length-1; i++){
                if(somasSubjects[i]<menor){
                    menor=somasSubjects[i]; 
                }
            }
        }
        return menor;

    }
	
									//   4      6     0
	public static int bestprogrammer(int A, int K, int P){
		
		int dia = 1;
		int asha = P;
		int kelly = 0;
		
		if(asha < kelly){
			return 0;
		}
		while(asha > kelly){
			asha = A + asha;
			kelly = K + kelly;
			if(kelly > asha){
				break;
			}
			dia++;
		}
		return dia;
	}
}
	