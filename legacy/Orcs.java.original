/*

 Orcs - Alpha v.0.1.6
 Luiz Filipe - lfbasantos@gmail.com

 02/05/2016 - Implementação do Limite de HP por Level
 06/05/2016 - Modificações na Arena para processar Ataque Mágico
 
*/



import java.io.*;
import java.util.*;




	class cInterage {
		
		String vFrase = "";
		
		void mNovaFrase(String pNova) {
			vFrase = pNova;
		}

		void mFala() {
			System.out.println(vFrase);
		}
		
		String mPergunta() {
			Console console = System.console();
			String vInput = console.readLine("#-> ");
			return vInput;
		}
		
		String mPausa() {
			Console console = System.console();
			String vInput = console.readLine("Tecle ENTER para continuar...");
			return vInput;
		}
		
		void mLimpa() {
			for (int i = 0; i < 50; ++i) System.out.print("\n");
			try { new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); } catch(InterruptedException|IOException e) {}
		}
		
		void mLimpaWin() {
			try { new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); } catch(InterruptedException|IOException e) {}
		}
		
		void mWait() {
			try { Thread.sleep(1000); } catch(InterruptedException e) {};
		}
		
		
	}

	class cSalvaCarrega {
		
		String vNome = "";
		String vItem = "";
		String vClasse = "";
		Integer vMana;
		Integer vpch;
		Integer vpce;
		Integer	vpcxp; 
		Integer vpclvl;
		Integer vpcgold;
		Integer vpcpot;
		int vPos;
		
		void mConfiguraAtributos(String pNome, Integer ppch, Integer ppce, Integer ppcxp, Integer ppclvl, Integer ppcgold, Integer ppcpot, String pClasse, Integer pMana) {
			vNome=pNome;
			vClasse=pClasse;
			vpch=ppch;
			vpce=ppce;
			vpcxp=ppcxp;
			vpclvl=ppclvl;
			vpcgold=ppcgold;
			vpcpot=ppcpot;
			vMana=pMana;
		}

		public void mSalvar() {	
			try {
				File vArq = new File("orcdata/"+vNome+".gamesave");
				vArq.createNewFile();
				FileWriter vSalvar = new FileWriter(vArq);
				vSalvar.write(vpch+";"+vpce+";"+vpcxp+";"+vpclvl+";"+vpcgold+";"+vpcpot+";"+vClasse+";"+vMana);
				vSalvar.flush();
				vSalvar.close();
			} catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		
		
		/*
			Configura Level
		*/
		public int mConfiguraLevel(Integer pXP) {	
		
			int[] vIni = new int[10000];
			int[] vFim = new int[10000];
			int[] vFator = new int[10000];
			int vCont=2;
			vFator[1]=500;
			vIni[1]=1;
			vFim[1]=1;
			
			// Calcula Fatores;
			while (vCont < 10001)
			{	
				vFator[vCont] = vFator[vCont-1]+500;
				vIni[vCont]=(vFator[vCont] * ((vCont-1)*(vCont-1)));
				vFim[vCont]=(vFator[vCont] * ((vCont-1)*(vCont-1)));
				
				// Verifica Level
				if (pXP >= vIni[vCont-1] && pXP <= vFim[vCont])
				{
					return vCont;
				}
				vCont++;
			}
			
			return pXP;
		}
		
		/*
			Configura Habilidade
		*/
		public int mCalculaHabilidade(Integer pLvl) {	
		
			int vFator = 0;
			int vCont=0;
			int vPCH=1;
			
			// Calcula Fatores;
			while (vCont < 10001)
			{	
				vFator = vCont + 12;
				
				// Verifica Level
				if (vPCH == pLvl)
				{
					return vFator;
				}
				vCont+=2;
				vPCH++;
			}
			
			return pLvl;
		}

		/*
			Calcula Limite HP
		*/
		public int mCalculaLimiteHP(Integer pLvl, Integer pPCE) {	
		
			int vPCE=1;
			
			// Calcula Limite HP do Level
			vPCE = pLvl*10 + (pLvl*10);
			
			// Verifica se Atingiu Limite e mantém Limite do HP
			if (pPCE >= vPCE)
			{
				return vPCE;
			} else
			{
				return pPCE;
			}
			
		}


		/*
			Calcula Limite MP
		*/
		public int mCalculaLimiteMP(Integer pLvl, Integer pMana) {	
		
			int vMana=1;
			
			// Calcula Limite MP do Level
			vMana = pLvl*10 + (pLvl*20);
			
			// Verifica se Atingiu Limite e mantém Limite do MP
			if (pMana >= vMana)
			{
				return vMana;
			} else
			{
				return pMana;
			}
			
		}
		
		
		
		public String mGeraNomeOrc(Integer pId) {	
			Integer vCont=0;
			String vOrc="";
			String vStr="Gludbog;Ulgha;Kubguk;Duugh;Tobdakh;Zugrak;Drutrug;Thokgor;Zikgor;Bugrog;Brughnab;Mashnakh;Sugugh;Bludmuk;Sagdakh;Skagblug;Thughnab;Odbog;Ruglut;Garzug";
			while (vCont < pId)
			{
				List<String> ListaNome = Arrays.asList(vStr.split(";"));
				vOrc = ListaNome.get(vCont);
				vCont++;
			}
			return vOrc;
		}
		
		
		
		
		public void mInicializaBau(String pNome) {	
			vNome=pNome;
			String vItem1="1;Arma;1;1;100;1;Faca Pequena Atk 1 Dano 1 $100\n";
			String vItem2="2;Set;0;0;50;1;Roupas Simples Def 0 Absorve 0 $50\n";
			try {
				File vArq = new File("orcdata/"+vNome+".items");
				vArq.createNewFile();
				FileWriter vSalvar = new FileWriter(vArq);
				vSalvar.write(vItem1);
				vSalvar.write(vItem2);
				vSalvar.flush();
				vSalvar.close();
			} catch(IOException e)
			{
				e.printStackTrace();
			}
		}		
		
		/*
			Insere Item no Baú
		*/
		public void mInsereItemBau(String pNome, String pItem) {	
			vNome=pNome;
			vItem=pItem;
			try {
				File vArq = new File("orcdata/"+vNome+".items");
				FileWriter vSalvar = new FileWriter(vArq, true);
				vSalvar.write(vItem);
				vSalvar.flush();
				vSalvar.close();
			} catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		
		public String mCarregar(String pNome, int pPos) {
			vNome=pNome;
			int vCont=1;
			String vExitErro="ERR";
			String vExitOK="0";
			vPos=pPos;
			cInterage oDiz = new cInterage();
			
			try {
				Scanner vRegistro = new Scanner(new FileReader("orcdata/"+vNome+".gamesave"));
				vRegistro.useDelimiter(";");
				/*
					Processa Lista para Obter Valor 
				*/
				while (vRegistro.hasNext()) 
				{
					String vStr = vRegistro.next();
						if ("DONE".equals(vStr)) 
						{
							vRegistro.close();
							break;
						}
					if ( vCont == vPos ){
						vRegistro.close();
						return vStr;
					}
					vCont++;
				}
			} catch(IOException e)
			{
				oDiz.mNovaFrase("Personagem Não Encontrado.");
				oDiz.mFala();
				oDiz.mPausa();
				return vExitErro;
			}
			return vExitOK;
		}
		
		/*
			Método Carregar Baú
		*/		
		public String mCarregarBau(String pNome) {
			vNome=pNome;
			String vExitErro="ERR";
			String vExitOK="0";
			String vExit="FIM";
			String vItem;
			Integer vID=1;
			cInterage oDiz = new cInterage();
						
			try {
				Scanner vRegistro = new Scanner(new FileReader("orcdata/"+vNome+".items"));
				vRegistro.useDelimiter("\\n");
				/*
					Processa Registros
				*/
				while (vRegistro.hasNext()) 
				{
					String vStr = vRegistro.next();
						if ("DONE".equals(vStr)) 
						{
							vRegistro.close();
							return vExit;
						} 
					/*
						Monta um Array com a String, faz o Split pra mostrar apenas a descrição do Item.
					*/
					List<String> ListaBau = Arrays.asList(vStr.split(";"));
					vItem = ListaBau.get(6);
					oDiz.mNovaFrase("# [" + vID +"] "+ vItem);
					oDiz.mFala();
					vID++;
							
				}
				vRegistro.close();
			} catch(IOException e)
			{
				oDiz.mNovaFrase("Baú Não Encontrado.");
				oDiz.mFala();
				oDiz.mPausa();
				return vExitErro;
			}
			return vExitOK;
		}
		
		/*
			Método Carregar Item Equipado
		*/		
		public String mCarregaItemEquipado(String pNome, String pTipo, String pParam) {
			// pParam indica qual valor o método deve retornar, sendo:
			// 1 - Nome do Item
			// 2 - Valor Bônus 1
			// 3 - Valor Bônus 2
			vNome=pNome;
			String vParam=pParam;
			String vEquipado = "";
			String vTipoItem = pTipo;
			String vTipoItemEquipado = "";
			String vNomeItem = "";
			String vBonus1 = "";
			String vBonus2 = "";
			String vExit="N/A";
			String vErro="ERR";
			Integer vID=1;
			Integer vDebug = 0;
			cInterage oDiz = new cInterage();
						
			try {
				Scanner vRegistro = new Scanner(new FileReader("orcdata/"+vNome+".items"));
				vRegistro.useDelimiter("\\n");
				/*
					Processa Registros
				*/
				while (vRegistro.hasNext()) 
				{
					String vStr = vRegistro.next();
						if ("DONE".equals(vStr)) 
						{
							if (vTipoItem.equals("2") || vTipoItem.equals("2")){
								vRegistro.close();
								return "0";
								} else if (vTipoItem.equals("1")) {
									vRegistro.close();
									return vExit;
								}
						} 
					
					// Verifica se o Item está Equipado
					List<String> ListaBau = Arrays.asList(vStr.split(";"));
					vEquipado = ListaBau.get(5);
					vNomeItem = ListaBau.get(6);
					vBonus1 = ListaBau.get(2);
					vBonus2 = ListaBau.get(3);
					vTipoItemEquipado = ListaBau.get(1);
					
					if (vDebug == 1) {
						oDiz.mNovaFrase("# Item: " + vNomeItem);
						oDiz.mFala();
						oDiz.mNovaFrase("# Bonus1: " + vBonus1);
						oDiz.mFala();
						oDiz.mNovaFrase("# Bonus2: " + vBonus2);
						oDiz.mFala();
						oDiz.mNovaFrase("# Tipo Item: " + vTipoItem);
						oDiz.mFala();
						oDiz.mNovaFrase("# TipoEquipado: " + vTipoItemEquipado);
						oDiz.mFala();
					}
					
					if (vEquipado.equals("1") && vTipoItem.equals(vTipoItemEquipado)) {
					
						if (vParam.equals("1")){
							oDiz.mNovaFrase("# " + vNomeItem);
							oDiz.mFala();
							vRegistro.close();
							return vNomeItem;
						}
						
						if (vParam.equals("2")){
							oDiz.mNovaFrase("# Bônus 1: " + vBonus1);
							oDiz.mFala();
							vRegistro.close();
							return vBonus1;
						}
						
						if (vParam.equals("3")){
							oDiz.mNovaFrase("# Bônus 2: " + vBonus2);
							oDiz.mFala();
							vRegistro.close();
							return vBonus2;
						}
						
						vEquipado="0";
						vTipoItem="";
					} 
					
					vID++;
							
				}
				vRegistro.close();
			} catch(IOException e)
			{
				oDiz.mNovaFrase("Baú Não Encontrado.");
				oDiz.mFala();
				oDiz.mPausa();
				return vErro;
			} 
			
			if (vTipoItem.equals("2") || vTipoItem.equals("2")){
								return "0";
								} else if (vTipoItem.equals("1")) {
									return vExit;
								}
			return vExit;
		}
		
		/*
			Vender Item
		*/		
		public Integer mVenderItem(String pNome, String pItem, Integer pGold) {
			vNome=pNome;
			Integer vGold=pGold;
			String vID=pItem;
			Integer vIDRegistro = 1;
			Integer vExitErro=9;
			cInterage oDiz = new cInterage();

			try {
				
				// Abre Arquivo Temporário
				File vArq = new File("orcdata/"+vNome+".items.temp");
				File vArq2 = new File("orcdata/"+vNome+".items");
				vArq.createNewFile();
				FileWriter vSalvar = new FileWriter(vArq);
				Scanner vRegistro = new Scanner(new FileReader("orcdata/"+vNome+".items"));
				vRegistro.useDelimiter("\\n");
				/*
					Processa Registros
				*/
				while (vRegistro.hasNext()) 
				{
					String vStr = vRegistro.next();
						if ("DONE".equals(vStr)) 
						{
							vRegistro.close();
							vSalvar.close();
							return vGold;
						} 
					/*
						Verifica Preço do Item e Calcula Venda
					*/
					List<String> ListaBau = Arrays.asList(vStr.split(";"));
					vItem = ListaBau.get(4);
					if (vIDRegistro != Integer.parseInt(vID)) {
						vSalvar.write(vStr+"\n");
						vGold = vGold + Integer.parseInt(vItem);
					}
					
					vIDRegistro++;
							
				}
				vRegistro.close();
				vSalvar.flush();
				vSalvar.close();
				//Rename do arquivo temporário para o novo arquivo.
				boolean vSucesso = vArq.renameTo(vArq2);
				if (vSucesso){
					oDiz.mNovaFrase("#");
					oDiz.mFala();	
				}
				
				
			} catch(IOException e)
			{
				oDiz.mNovaFrase("Baú Não Encontrado.");
				oDiz.mFala();
				oDiz.mPausa();
				return vExitErro;
			}

			return vGold;
		}
		

		/*
			Equipar Item
		*/
		public Integer mEquiparItem(String pNome, String pItem) {
			vNome=pNome;
			String vTipo="N/A";
			Integer vID=Integer.parseInt(pItem);
			String vReg;
			Integer vIDRegistro = 1;
			Integer vExitErro=9;
			Integer vExit=0;
			Integer vDebug=0;
			cInterage oDiz = new cInterage();
			
			if (vDebug==1) {
				oDiz.mNovaFrase("DEBUG: NOME [" + vNome + "] - ITEM [" + vID +"]");
				oDiz.mFala();
				oDiz.mPausa();
			}

			try {
				// Abre Arquivo Temporário
				File vArq = new File("orcdata/"+vNome+".items.temp");
				File vArq2 = new File("orcdata/"+vNome+".items");
				vArq.createNewFile();
				FileWriter vSalvar = new FileWriter(vArq);
				
				// Verifica o Tipo e a Classe do Item
				Scanner vRegistro = new Scanner(new FileReader("orcdata/"+vNome+".items"));
				vRegistro.useDelimiter("\\n");
				while (vRegistro.hasNext()) 
				{
					String vStr = vRegistro.next();
						if ("DONE".equals(vStr)) 
						{
							vSalvar.close();
							vRegistro.close();
							return vExit;
						}
					if ( vID == vIDRegistro ) {
						List<String> ListaBau = Arrays.asList(vStr.split(";"));
						vTipo = ListaBau.get(1);
						if (vDebug==1) {
							oDiz.mNovaFrase("DEBUG: TIPO - " +vTipo);
							oDiz.mFala();
							oDiz.mPausa();
							}
					}
					vIDRegistro++;
				}
				vRegistro.close();
				
				// Atualiza Flag para Zero para todos os Itens do Tipo Selecionado
				Scanner vRegistro2 = new Scanner(new FileReader("orcdata/"+vNome+".items"));
				vRegistro2.useDelimiter("\\n");
				vIDRegistro=1;
				while (vRegistro2.hasNext()) 
				{
					String vStr2 = vRegistro2.next();
						if ("DONE".equals(vStr2)) 
						{
							vSalvar.close();
							vRegistro.close();
							vRegistro2.close();
							return vExit;
						}
					List<String> ListaBau = Arrays.asList(vStr2.split(";"));
					if (ListaBau.get(1).equals(vTipo)) {
							if (vDebug==1) {
								oDiz.mNovaFrase("Item Tipo: " + vTipo + " Linha :[" + vIDRegistro + "]");
								oDiz.mFala();
								oDiz.mPausa();
							}
						vReg = ListaBau.get(0)+";"+ListaBau.get(1)+";"+ListaBau.get(2)+";"+ListaBau.get(3)+";"+ListaBau.get(4)+";0;"+ListaBau.get(6);
						if (vDebug==1) {
								oDiz.mNovaFrase("REGISTRO1: " + vReg);
								oDiz.mFala();
								oDiz.mPausa();
							}
						vSalvar.write(vReg+"\n");
						vSalvar.flush();
					} else {
						vReg = ListaBau.get(0)+";"+ListaBau.get(1)+";"+ListaBau.get(2)+";"+ListaBau.get(3)+";"+ListaBau.get(4)+";"+ListaBau.get(5)+";"+ListaBau.get(6);
						vSalvar.write(vStr2+"\n");
						if (vDebug==1) {
								oDiz.mNovaFrase("Item Tipo: " + vTipo + " Linha :[" + vIDRegistro + "]");
								oDiz.mFala();
								oDiz.mNovaFrase("REGISTRO2: " + vReg);
								oDiz.mFala();
								oDiz.mPausa();
							}
					}
					vIDRegistro++;
				}
				vRegistro2.close();
				vSalvar.close();
				boolean vSucesso = vArq.renameTo(vArq2);
				if (vSucesso){
					oDiz.mNovaFrase("# ");
					oDiz.mFala();
				}
				
						if (vDebug==1) {
								oDiz.mNovaFrase("FLAGS ATUALIZADOS PRA ZERO");
								oDiz.mFala();
								oDiz.mPausa();
							}
				
				// Atualiza Item para Flag 1
				vIDRegistro=1;
				File vArq3 = new File("orcdata/"+vNome+".items.temp");
				File vArq4 = new File("orcdata/"+vNome+".items");
				vArq3.createNewFile();
				FileWriter vSalvar2 = new FileWriter(vArq3);
				Scanner vRegistro3 = new Scanner(new FileReader("orcdata/"+vNome+".items"));
				vRegistro3.useDelimiter("\\n");
				while (vRegistro3.hasNext()) 
				{
					String vStr3 = vRegistro3.next();
						if ("DONE".equals(vStr3)) 
						{
							vSalvar2.close();
							vRegistro.close();
							vRegistro2.close();
							vRegistro3.close();
							return vExit;
						}
					
					List<String> ListaBau = Arrays.asList(vStr3.split(";"));
					
					if (vIDRegistro == vID) {
					
						if (vDebug==1) {
								oDiz.mNovaFrase("Item Encontrado: " + vID);
								oDiz.mFala();
								oDiz.mPausa();
							}
					
						
						if (ListaBau.get(1).equals(vTipo)) {
							vReg = ListaBau.get(0)+";"+ListaBau.get(1)+";"+ListaBau.get(2)+";"+ListaBau.get(3)+";"+ListaBau.get(4)+";1;"+ListaBau.get(6);
							vSalvar2.write(vReg+"\n");
							vSalvar2.flush();
								if (vDebug==1) {
									oDiz.mNovaFrase("REGISTRO1: " + vReg);
									oDiz.mFala();
									oDiz.mPausa();
								}
						} else
						{
						vReg = ListaBau.get(0)+";"+ListaBau.get(1)+";"+ListaBau.get(2)+";"+ListaBau.get(3)+";"+ListaBau.get(4)+";"+ListaBau.get(5)+";"+ListaBau.get(6);
						vSalvar2.write(vStr3+"\n");											
								if (vDebug==1) {
									oDiz.mNovaFrase("REGISTRO2: " + vReg);
									oDiz.mFala();
									oDiz.mPausa();
								}
						}
					} else {
					
						vReg = ListaBau.get(0)+";"+ListaBau.get(1)+";"+ListaBau.get(2)+";"+ListaBau.get(3)+";"+ListaBau.get(4)+";"+ListaBau.get(5)+";"+ListaBau.get(6);
						vSalvar2.write(vStr3+"\n");											
								if (vDebug==1) {
									oDiz.mNovaFrase("REGISTRO2: " + vReg);
									oDiz.mFala();
									oDiz.mPausa();
								}
					}
					vIDRegistro++;
				}
				vSalvar2.close();
				vRegistro3.close();
				boolean vSucesso2 = vArq3.renameTo(vArq4);
				if (vSucesso2){
					oDiz.mNovaFrase("#");
					oDiz.mFala();
				}
				
			} catch(IOException e)
			{
				oDiz.mNovaFrase("Baú Não Encontrado.");
				oDiz.mFala();
				oDiz.mPausa();
				return vExitErro;
			}
			return vExit;
		}
		
	}	
	
	class cProcessaArena {
		
		public static int mDados(int vMin, int vMax) {
			Random rand = new Random();
			int randomNum = rand.nextInt((vMax - vMin) + 1) + vMin;
			return randomNum;
		}
		
		public static int mNpcH(int pLvl) {
			int vMin=6;
			int vMax=18;
			Random rand = new Random();
			int randomNum = rand.nextInt((vMax - vMin) + 1) + vMin;
			randomNum=randomNum + pLvl;
			return randomNum;
		}
		
		public static int mNpcE(int pLvl) {
			int vMin=6;
			int vMax=18;
			Random rand = new Random();
			int randomNum = rand.nextInt((vMax - vMin) + 1) + vMin;
			randomNum=randomNum + pLvl;
			return randomNum;
		}		
		
		public static int mNpcXP(int pLvl) {
			int vMin=100;
			int vMax=500;
			Random rand = new Random();
			int randomNum = rand.nextInt((vMax - vMin) + 1) + vMin;
			randomNum=randomNum * pLvl;
			return randomNum;
		}
		
		public static int mNpcGold(int pLvl) {
			int vMin=500;
			int vMax=1000;
			Random rand = new Random();
			int randomNum = rand.nextInt((vMax - vMin) + 1) + vMin;
			randomNum=randomNum * pLvl;
			return randomNum;
		}
		
	}


	public class Orcs {

		public static void main(String[] args) {
			/*
				Variáveis de PC e NPC
			*/
			String vOpt, vOptMenu, vOptLojaItens, vOptArena;
			String vNome = "";
			String vNomeNPC = "Drugul";
			String vClasse ="";
			Integer vpch=0;
			Integer vpce=0;
			Integer vMana=0;
			Integer vpcxp=0;
			Integer vpclvl=0;
			Integer vpcgold=0;
			Integer vpcpot=0;
			Integer vnpch=0;
			Integer vnpce=0;
			Integer vnpcxp=0;
			Integer vnpcgold=0;
			String vVerificaLoad="0";
			int vInicioOk=1;
			int vExit=0;
			int vExitMenu=0;
			int vExitLojaItens=0;
			int vExitArena=0;
			
			do {
			
			/*
				Declara Objeto que Interage com a Tela (Objeto Diz, Métodos Fala e Pergunta)
			*/
			cInterage oDiz = new cInterage();
			
			/*
				Declara Objeto Save/Load
			*/
			cSalvaCarrega oGrava = new cSalvaCarrega();
			
			/*
				Declara Objeto Processa Arena
			*/
			cProcessaArena oArena = new cProcessaArena();
			
			/*
				Limpando a Tela
			*/
			oDiz.mLimpa();
			oDiz.mLimpaWin();
			
			
			/*
				Caso não exista cria o diretorio orcdata
			*/
			File theDir = new File("orcdata");

			if (!theDir.exists()) {
				//oDiz.mNovaFrase("# Diretório orcdata não encontrado. Criando.");
				//oDiz.mFala();
				boolean result = false;

				try{
					theDir.mkdir();
					result = true;
				} 
				catch(SecurityException se){
					oDiz.mNovaFrase("# Erro! Não foi possível criar diretório orcdata. Crie o diretório manualmente ou execute em um diretório com permissões de escrita.");
					oDiz.mFala();
				}        
				if(result) {    

				}
			}
			
			
			/*
				Tela Inicial
			*/
			oDiz.mNovaFrase("#");
			oDiz.mFala();
			oDiz.mNovaFrase("# ORCS v.0.1.6");
			oDiz.mFala();
			oDiz.mNovaFrase("#");
			oDiz.mFala();
			oDiz.mNovaFrase("# Bem-vindo ao mundo Sangrento dos Orcs!");
			oDiz.mFala();
			oDiz.mNovaFrase("#");
			oDiz.mFala();
			oDiz.mNovaFrase("# Escolha suas opções");
			oDiz.mFala();
			oDiz.mNovaFrase("# [1] Novo Pergonagem");
			oDiz.mFala();
			oDiz.mNovaFrase("# [2] Carregar Personagem");
			oDiz.mFala();
			oDiz.mNovaFrase("# [X] Encerrar");
			oDiz.mFala();
			
			/*
				Verifica a Opção Selecionada pelo Usuário
			*/
			vOpt = oDiz.mPergunta();
			
			/*
				Opção Novo Personagem
			*/
			if (vOpt.equals("1")) 
			{
				/*
					Obtém Nome do Personagem
				*/
				oDiz.mLimpa();
				oDiz.mNovaFrase("# Digite o Nome do Personagem");
				oDiz.mFala();
				vNome = oDiz.mPergunta();
				
				/*
					Inicializa Baú
				*/
				oGrava.mInicializaBau(vNome);
				
				/*
					Menu de Seleção de Classes
				*/
				oDiz.mLimpa();
				oDiz.mNovaFrase("#");
				oDiz.mFala();
				oDiz.mNovaFrase("# Escolha sua Classe");
				oDiz.mFala();
				oDiz.mNovaFrase("#");
				oDiz.mFala();
				oDiz.mNovaFrase("# [1] Escola de Armas");
				oDiz.mFala();
				oDiz.mNovaFrase("# Especialização em Armas e Armaduras.");
				oDiz.mFala();
				oDiz.mNovaFrase("");
				oDiz.mFala();
				oDiz.mNovaFrase("# [2] Escola de Magia");
				oDiz.mFala();
				oDiz.mNovaFrase("# Especialização em Magias e Encantamentos.");
				oDiz.mFala();
				vClasse = oDiz.mPergunta();
				
				/*
					Guerreiro
				*/
				if (vClasse.equals("1")) {
					oDiz.mLimpa();
					vpch=12;
					vpce=16;
					vMana=0;
					vpcpot=5;
					vpcxp=0;
					vpclvl=1;
					vpcgold=1000;
					oGrava.mConfiguraAtributos(vNome, vpch, vpce, vpcxp, vpclvl, vpcgold, vpcpot, vClasse, vMana);
					oGrava.mSalvar();
					oDiz.mNovaFrase("# Personagem Gerado e Gravado.");
					oDiz.mFala();
					vInicioOk=0;
					oDiz.mPausa();
				}
				
				/*
					Mago
				*/
				if (vClasse.equals("2")) {
					oDiz.mLimpa();
					vpch=12;
					vpce=12;
					vMana=20;
					vpcpot=3;
					vpcxp=0;
					vpclvl=1;
					vpcgold=1000;
					oGrava.mConfiguraAtributos(vNome, vpch, vpce, vpcxp, vpclvl, vpcgold, vpcpot, vClasse, vMana);
					oGrava.mSalvar();
					oDiz.mNovaFrase("# Personagem Gerado e Gravado.");
					oDiz.mFala();
					vInicioOk=0;
					oDiz.mPausa();
				}
				vOpt="0";
			}
			
			/*
				Carregar Personagem
			*/
			if (vOpt.equals("2")) 
			{
				oDiz.mNovaFrase("# Digite o Nome do Personagem");
				oDiz.mFala();
				vNome = oDiz.mPergunta();
				vVerificaLoad=oGrava.mCarregar(vNome,1);
				if (!vVerificaLoad.equals("ERR")) 
				{
					vpch=Integer.parseInt(oGrava.mCarregar(vNome,1));
					vpce=Integer.parseInt(oGrava.mCarregar(vNome,2));
					vpcxp=Integer.parseInt(oGrava.mCarregar(vNome,3));
					vpclvl=Integer.parseInt(oGrava.mCarregar(vNome,4));
					vpcgold=Integer.parseInt(oGrava.mCarregar(vNome,5));
					vpcpot=Integer.parseInt(oGrava.mCarregar(vNome,6));
					vClasse=oGrava.mCarregar(vNome,7);
					vMana=Integer.parseInt(oGrava.mCarregar(vNome,8));
					vInicioOk=0;
				}
				oDiz.mPausa();
				vOpt="0";
			}
			
			/*
				Sair / Encerrar
			*/
			if (vOpt.equals("X")) 
			{
				oDiz.mLimpa();
				oDiz.mNovaFrase("# Sair");
				System.exit(0);
			}
			
			/*BigDecimal vIni = new BigDecimal(1.0);
			cCalculaMatriz oMatriz = new cCalculaMatriz();
			oMatriz.mMatriz(vIni);*/
			
			
			/*
				Processa Menu In-Game
			*/
			while (vExitMenu==0 && vInicioOk==0) 
			{
				/*
					Limpando a Tela
				*/
				oDiz.mLimpa();
				/*
					Tela Menu
				*/
				oDiz.mNovaFrase("#");
				oDiz.mFala();
				oDiz.mNovaFrase("# MENU");
				oDiz.mFala();
				oDiz.mNovaFrase("#");
				oDiz.mFala();
				oDiz.mNovaFrase("# [1] Ver Ficha");
				oDiz.mFala();
				oDiz.mNovaFrase("# [2] Ver Baú");
				oDiz.mFala();
				oDiz.mNovaFrase("# [3] Loja de Itens");
				oDiz.mFala();
				oDiz.mNovaFrase("# [4] Taverna");
				oDiz.mFala();
				oDiz.mNovaFrase("# [5] Armas & Armaduras");
				oDiz.mFala();
				oDiz.mNovaFrase("# [6] Cajados & Magias");
				oDiz.mFala();
				oDiz.mNovaFrase("# [7] Arena");
				oDiz.mFala();
				//oDiz.mNovaFrase("# [8] Missões");
				//oDiz.mFala();
				oDiz.mNovaFrase("# [X] Sair");
				oDiz.mFala();
				
				/*
					Verifica a Opção Selecionada pelo Usuário
				*/
				oDiz.mNovaFrase("#->");
				oDiz.mFala();				
				vOptMenu = oDiz.mPergunta();
				
				/*
					Sair / Encerrar
				*/
				if (vOptMenu.equals("X")) 
				{
					oDiz.mLimpa();
					oDiz.mNovaFrase("# Sair");
					vExitMenu=1;
				}
				
				
				/*
					Ver Ficha
				*/
				if (vOptMenu.equals("1")) 
				{
					oDiz.mLimpa();
					oDiz.mNovaFrase("#");
					oDiz.mFala();
					oDiz.mNovaFrase("# Ficha do Personagem");
					oDiz.mFala();
					oDiz.mNovaFrase("#");
					oDiz.mFala();
					/*
						Atualiza Valores
					*/
					vpch=Integer.parseInt(oGrava.mCarregar(vNome,1));
					vpce=Integer.parseInt(oGrava.mCarregar(vNome,2));
					vpcxp=Integer.parseInt(oGrava.mCarregar(vNome,3));
					vpclvl=Integer.parseInt(oGrava.mCarregar(vNome,4));
					vpcgold=Integer.parseInt(oGrava.mCarregar(vNome,5));
					vpcpot=Integer.parseInt(oGrava.mCarregar(vNome,6));
					vClasse=oGrava.mCarregar(vNome,7);
					vMana=Integer.parseInt(oGrava.mCarregar(vNome,8));
					/*
						Desenha Ficha
					*/
						if (vClasse.equals("1")) {
							oDiz.mNovaFrase("# Classe: Escola de Armas");
							oDiz.mFala();
						} else {
							oDiz.mNovaFrase("# Classe: Escola de Magia");
							oDiz.mFala();
						}
					oDiz.mNovaFrase("# Nome: "+vNome);
					oDiz.mFala();
					oDiz.mNovaFrase("# XP: "+vpcxp+" - Nível: "+vpclvl);
					oDiz.mFala();
					oDiz.mNovaFrase("#");
					oDiz.mFala();
					oDiz.mNovaFrase("# Habilidade: "+vpch);
					oDiz.mFala();
					oDiz.mNovaFrase("# Pontos de Vida: "+vpce);
					oDiz.mFala();
						if (vClasse.equals("2")) {
							oDiz.mNovaFrase("# Pontos de Mana: " + vMana);
							oDiz.mFala();
						}
					oDiz.mNovaFrase("#");
					oDiz.mFala();
					oDiz.mNovaFrase("# Poções: "+vpcpot);
					oDiz.mFala();
					oDiz.mNovaFrase("# Gold: "+vpcgold);
					oDiz.mFala();
					oDiz.mNovaFrase("#");
					oDiz.mFala();
					
						if (vClasse.equals("1")) {
							oDiz.mNovaFrase("# Arma:");
							oDiz.mFala();
							oGrava.mCarregaItemEquipado(vNome, "Arma", "1");
							oDiz.mNovaFrase("# Set: ");
							oDiz.mFala();
							oGrava.mCarregaItemEquipado(vNome, "Set", "1");
						} else {
							oDiz.mNovaFrase("# Magia:");
							oDiz.mFala();
							oGrava.mCarregaItemEquipado(vNome, "Magia", "1");
							oDiz.mNovaFrase("# Manto: ");
							oDiz.mFala();
							oGrava.mCarregaItemEquipado(vNome, "Manto", "1");
						}
					
					oDiz.mNovaFrase("#");
					oDiz.mFala();					
					oDiz.mPausa();
				}
			
			
				/*
					Ver Baú
				*/
				if (vOptMenu.equals("2")) 
				{
					while (vExitLojaItens==0) 
					{
						oDiz.mLimpa();
						oDiz.mNovaFrase("#");
						oDiz.mFala();
						oDiz.mNovaFrase("# Baú do Personagem");
						oDiz.mFala();
						oDiz.mNovaFrase("#");
						oDiz.mFala();
						vpcgold=Integer.parseInt(oGrava.mCarregar(vNome,5));
						vpcpot=Integer.parseInt(oGrava.mCarregar(vNome,6));					
						
						/*
							Mostra Itens
						*/
						oDiz.mNovaFrase("# Nome: "+vNome);
						oDiz.mFala();
						oDiz.mNovaFrase("# Poções: "+vpcpot);
						oDiz.mFala();
						oDiz.mNovaFrase("# Gold: "+vpcgold);
						oDiz.mFala();
						oDiz.mNovaFrase("#");
						oDiz.mFala();
						oGrava.mCarregarBau(vNome);
						oDiz.mNovaFrase("#");
						oDiz.mFala();
						oDiz.mNovaFrase("# [E] Equipar Item ");
						oDiz.mFala();						
						oDiz.mNovaFrase("# [V] Vender ");
						oDiz.mFala();
						oDiz.mNovaFrase("# [X] Sair");
						oDiz.mFala();						
						vOptLojaItens = oDiz.mPergunta();

						if (vOptLojaItens.equals("V")) {
								oDiz.mNovaFrase("# Digite o ID do Item ou 0 para Cancelar.");
								oDiz.mFala();
								vOptLojaItens = oDiz.mPergunta();
								if (vOptLojaItens.equals("0")) {
									vExitLojaItens=0;
								} else {
									vpcgold = oGrava.mVenderItem(vNome, vOptLojaItens, vpcgold);
									oGrava.mConfiguraAtributos(vNome, vpch, vpce, vpcxp, vpclvl, vpcgold, vpcpot, vClasse, vMana);
								    oGrava.mSalvar();
									oDiz.mNovaFrase("# Item ["+ vOptLojaItens +"] Vendido.");
									oDiz.mFala();
									oDiz.mPausa();
								}
							}
							
						if (vOptLojaItens.equals("E")) {
								oDiz.mNovaFrase("# Digite o ID do Item ou 0 para Cancelar.");
								oDiz.mFala();
								vOptLojaItens = oDiz.mPergunta();
								if (vOptLojaItens.equals("0")) {
									vExitLojaItens=0;
								} else {
									oGrava.mEquiparItem(vNome, vOptLojaItens);
									oDiz.mNovaFrase("# Item ["+ vOptLojaItens +"] Equipado.");
									oDiz.mFala();
									oDiz.mPausa();
								}
							}							
							
						if (vOptLojaItens.equals("X")) {
								vExitLojaItens=1;
							}	
					}
					vExitLojaItens=0;
				}
				
				/*
					Processa Loja de Itens
				*/
				if (vOptMenu.equals("3")) {
					while (vExitLojaItens==0) 
					{
						/*
							Mostra Opções da Loja
						*/
						vpcgold=Integer.parseInt(oGrava.mCarregar(vNome,5));
						vpcpot=Integer.parseInt(oGrava.mCarregar(vNome,6));							
						oDiz.mLimpa();
						oDiz.mNovaFrase("#");
						oDiz.mFala();
						oDiz.mNovaFrase("# LOJA DE ITENS");
						oDiz.mFala();
						oDiz.mNovaFrase("#");
						oDiz.mFala();
						oDiz.mNovaFrase("# Poções: "+vpcpot);
						oDiz.mFala();
						oDiz.mNovaFrase("# Gold: "+vpcgold);
						oDiz.mFala();
						oDiz.mNovaFrase("#");
						oDiz.mFala();
						oDiz.mNovaFrase("# [1] [50] Poção de HP 1D");
						oDiz.mFala();
						oDiz.mNovaFrase("# [X] Sair");
						oDiz.mFala();
						vOptLojaItens = oDiz.mPergunta();
						
						
						/*
							Processa Compras
						*/
						if (vOptLojaItens.equals("1")) {
							if (vpcgold < 50) {
								oDiz.mNovaFrase("# Dinheiro insuficiente");
								oDiz.mFala();
								oDiz.mPausa();
							} else {
								vpcgold = vpcgold - 50;
								vpcpot++;
								oGrava.mConfiguraAtributos(vNome, vpch, vpce, vpcxp, vpclvl, vpcgold, vpcpot, vClasse, vMana);
								oGrava.mSalvar();
							}
						}
						
						if (vOptLojaItens.equals("X")) {
							vExitLojaItens=1;
						}
							
					}
						vExitLojaItens=0;
				}
				
				
				/*
					Processa Taverna
				*/
				if (vOptMenu.equals("4")) {
					while (vExitLojaItens==0) 
					{
						/*
							Mostra Opções da Taverna
						*/
						vpcgold=Integer.parseInt(oGrava.mCarregar(vNome,5));
						vpce=Integer.parseInt(oGrava.mCarregar(vNome,2));
						oDiz.mLimpa();
						oDiz.mNovaFrase("#");
						oDiz.mFala();
						oDiz.mNovaFrase("# TAVERNA DO CÃO SARNENTO");
						oDiz.mFala();
						oDiz.mNovaFrase("#");
						oDiz.mFala();
						oDiz.mNovaFrase("# Gold: "+vpcgold);
						oDiz.mFala();
						oDiz.mNovaFrase("#");
						oDiz.mFala();
						oDiz.mNovaFrase("# Pontos de Vida: "+vpce);
						oDiz.mFala();
						oDiz.mNovaFrase("#");						
						oDiz.mFala();
						oDiz.mNovaFrase("# [1] [10] Cerveja - Recupera 2 Pontos de HP/MP");
						oDiz.mFala();
						oDiz.mNovaFrase("#");
						oDiz.mFala();
						oDiz.mNovaFrase("# [2] [50] Vinho - Recupera 5 Pontos de HP/MP");
						oDiz.mFala();
						oDiz.mNovaFrase("#");
						oDiz.mFala();
						oDiz.mNovaFrase("# [3] [150] Chá de Cogumelo - Recupera 10 Pontos de HP/MP");
						oDiz.mFala();						
						oDiz.mNovaFrase("#");
						oDiz.mFala();						
						oDiz.mNovaFrase("# [X] Sair");
						oDiz.mFala();
						vOptLojaItens = oDiz.mPergunta();
						
						/*
							Processa Compras
						*/
						if (vOptLojaItens.equals("1")) {
							if (vpcgold < 10) {
								oDiz.mNovaFrase("# Dinheiro insuficiente");
								oDiz.mFala();
								oDiz.mPausa();
							} else {
								vpcgold = vpcgold - 10;
								vpcpot++;
								vpce = vpce + 2;
								vMana = vMana + 2;
								vpce = oGrava.mCalculaLimiteHP(vpclvl, vpce);
								vMana = oGrava.mCalculaLimiteMP(vpclvl, vMana);
								oGrava.mConfiguraAtributos(vNome, vpch, vpce, vpcxp, vpclvl, vpcgold, vpcpot, vClasse, vMana);
								oGrava.mSalvar();
							}
						}
						
						if (vOptLojaItens.equals("2")) {
							if (vpcgold < 50) {
								oDiz.mNovaFrase("# Dinheiro insuficiente");
								oDiz.mFala();
								oDiz.mPausa();
							} else {
								vpcgold = vpcgold - 50;
								vpcpot++;
								vpce = vpce + 5;
								vMana = vMana + 5;
								vpce = oGrava.mCalculaLimiteHP(vpclvl, vpce);
								vMana = oGrava.mCalculaLimiteMP(vpclvl, vMana);
								oGrava.mConfiguraAtributos(vNome, vpch, vpce, vpcxp, vpclvl, vpcgold, vpcpot, vClasse, vMana);
								oGrava.mSalvar();
							}
						}
						
						if (vOptLojaItens.equals("3")) {
							if (vpcgold < 150) {
								oDiz.mNovaFrase("# Dinheiro insuficiente");
								oDiz.mFala();
								oDiz.mPausa();
							} else {
								vpcgold = vpcgold - 150;
								vpcpot++;
								vpce = vpce + 10;
								vMana = vMana + 10;
								vpce = oGrava.mCalculaLimiteHP(vpclvl, vpce);
								vMana = oGrava.mCalculaLimiteMP(vpclvl, vMana);
								oGrava.mConfiguraAtributos(vNome, vpch, vpce, vpcxp, vpclvl, vpcgold, vpcpot, vClasse, vMana);
								oGrava.mSalvar();
							}
						}
						
						if (vOptLojaItens.equals("X")) {
							vExitLojaItens=1;
						}
							
					}
						vExitLojaItens=0;
				}
				
				/*
					Processa Loja de Armas & Armaduras
				*/
				if (vOptMenu.equals("5")) {
					String vItem1 = "1;Arma;1;1;100;0;Faca Pequena Atk 1 Dano 1 $100";
					String vItem2 = "2;Arma;2;2;500;0;Espada Atk 2 Dano 2 $1000";
					String vItem3 = "3;Set;0;1;50;0;Roupas Simples Def 0 Abs 1 $100";
					String vItem4 = "4;Set;1;2;500;0;Set de Couro Def 1 Abs 2 $1000";
					int vPrecoItem1=100;
					int vPrecoItem2=1000;
					int vPrecoItem3=100;
					int vPrecoItem4=1000;
					while (vExitLojaItens==0) 
					{
						/*
							Mostra Opções da Loja
						*/
						vpcgold=Integer.parseInt(oGrava.mCarregar(vNome,5));
						oDiz.mLimpa();
						oDiz.mNovaFrase("#");
						oDiz.mFala();
						oDiz.mNovaFrase("# ARMAS & ARMADURAS");
						oDiz.mFala();
						oDiz.mNovaFrase("#");
						oDiz.mFala();
						oDiz.mNovaFrase("# Gold: "+vpcgold);
						oDiz.mFala();
						oDiz.mNovaFrase("#");
						oDiz.mFala();
						oDiz.mNovaFrase("# [1] Faca Pequena Atk 1 Dano 1 $100");
						oDiz.mFala();
						oDiz.mNovaFrase("# [2] Espada Atk 2 Dano 2 $1000");
						oDiz.mFala();
						oDiz.mNovaFrase("# [3] Roupas Simples Def 0 Abs 1 $100");
						oDiz.mFala();
						oDiz.mNovaFrase("# [4] Set de Couro Def 1 Abs 2 $1000");
						oDiz.mFala();
						oDiz.mNovaFrase("#");
						oDiz.mFala();
						oDiz.mNovaFrase("# [X] Sair");
						oDiz.mFala();
						vOptLojaItens = oDiz.mPergunta();
						
						/*
							Processa Compras
						*/
						if (vOptLojaItens.equals("1")) {
							if (vpcgold < vPrecoItem1) {
								oDiz.mNovaFrase("# Dinheiro insuficiente");
								oDiz.mFala();
								oDiz.mPausa();
							} else {
								// Subtrai Ouro e Adiciona Item.
								vpcgold = vpcgold - vPrecoItem1;
								oGrava.mInsereItemBau(vNome, vItem1 + "\n");
								oGrava.mConfiguraAtributos(vNome, vpch, vpce, vpcxp, vpclvl, vpcgold, vpcpot, vClasse, vMana);
								oGrava.mSalvar();	
								oDiz.mNovaFrase("# Item comprado com sucesso.");
								oDiz.mFala();
								oDiz.mPausa();
							}
						}
						
						if (vOptLojaItens.equals("2")) {
							if (vpcgold < vPrecoItem2) {
								oDiz.mNovaFrase("# Dinheiro insuficiente");
								oDiz.mFala();
								oDiz.mPausa();
							} else {
								// Subtrai Ouro e Adiciona Item.
								vpcgold = vpcgold - vPrecoItem2;
								oGrava.mInsereItemBau(vNome, vItem2 + "\n");
								oGrava.mConfiguraAtributos(vNome, vpch, vpce, vpcxp, vpclvl, vpcgold, vpcpot, vClasse, vMana);
								oGrava.mSalvar();	
								oDiz.mNovaFrase("# Item comprado com sucesso.");
								oDiz.mFala();
								oDiz.mPausa();
							}
						}
						
						if (vOptLojaItens.equals("3")) {
							if (vpcgold < vPrecoItem3) {
								oDiz.mNovaFrase("# Dinheiro insuficiente");
								oDiz.mFala();
								oDiz.mPausa();
							} else {
								// Subtrai Ouro e Adiciona Item.
								vpcgold = vpcgold - vPrecoItem3;
								oGrava.mInsereItemBau(vNome, vItem3 + "\n");
								oGrava.mConfiguraAtributos(vNome, vpch, vpce, vpcxp, vpclvl, vpcgold, vpcpot, vClasse, vMana);
								oGrava.mSalvar();	
								oDiz.mNovaFrase("# Item comprado com sucesso.");
								oDiz.mFala();
								oDiz.mPausa();
							}
						}
						
						if (vOptLojaItens.equals("4")) {
							if (vpcgold < vPrecoItem4) {
								oDiz.mNovaFrase("# Dinheiro insuficiente");
								oDiz.mFala();
								oDiz.mPausa();
							} else {
								// Subtrai Ouro e Adiciona Item.
								vpcgold = vpcgold - vPrecoItem4;
								oGrava.mInsereItemBau(vNome, vItem4 + "\n");
								oGrava.mConfiguraAtributos(vNome, vpch, vpce, vpcxp, vpclvl, vpcgold, vpcpot, vClasse, vMana);
								oGrava.mSalvar();	
								oDiz.mNovaFrase("# Item comprado com sucesso.");
								oDiz.mFala();
								oDiz.mPausa();
							}
						}
						
						if (vOptLojaItens.equals("X")) {
							vExitLojaItens=1;
						}
							
					}
						vExitLojaItens=0;
				}
				
				/*
					Processa Loja de Magias
				*/
				if (vOptMenu.equals("6")) {
					String vItem1 = "5;Magia;1;1;100;0;Bola de Fogo Atk 1 Dano 1 $100";
					String vItem2 = "6;Magia;2;2;500;0;Bola de Gelo Atk 2 Dano 2 $1000";
					String vItem3 = "7;Manto;0;1;50;0;Manto Aprendiz Def 0 Abs 1 $100";
					String vItem4 = "8;Manto;1;2;500;0;Manto Sagrado Def 1 Abs 2 $1000";
					int vPrecoItem1=100;
					int vPrecoItem2=1000;
					int vPrecoItem3=100;
					int vPrecoItem4=1000;
					while (vExitLojaItens==0) 
					{
						/*
							Mostra Opções da Loja
						*/
						vpcgold=Integer.parseInt(oGrava.mCarregar(vNome,5));
						oDiz.mLimpa();
						oDiz.mNovaFrase("#");
						oDiz.mFala();
						oDiz.mNovaFrase("# MANTOS & MAGIAS");
						oDiz.mFala();
						oDiz.mNovaFrase("#");
						oDiz.mFala();
						oDiz.mNovaFrase("# Gold: "+vpcgold);
						oDiz.mFala();
						oDiz.mNovaFrase("#");
						oDiz.mFala();
						oDiz.mNovaFrase("# [1] Bola de Fogo Atk 1 Dano 1 $100");
						oDiz.mFala();
						oDiz.mNovaFrase("# [2] Bola de Gelo Atk 2 Dano 2 $1000");
						oDiz.mFala();
						oDiz.mNovaFrase("# [3] Manto Aprendiz Def 0 Abs 1 $100");
						oDiz.mFala();						
						oDiz.mNovaFrase("# [4] Manto Sagrado Def 1 Abs 2 $1000");
						oDiz.mFala();												
						oDiz.mNovaFrase("#");
						oDiz.mFala();						
						oDiz.mNovaFrase("# [X] Sair");
						oDiz.mFala();
						vOptLojaItens = oDiz.mPergunta();
						
						/*
							Processa Compras
						*/
						if (vOptLojaItens.equals("1")) {
							if (vpcgold < vPrecoItem1) {
								oDiz.mNovaFrase("# Dinheiro insuficiente");
								oDiz.mFala();
								oDiz.mPausa();
							} else {
								// Subtrai Ouro e Adiciona Item.
								vpcgold = vpcgold - vPrecoItem1;
								oGrava.mInsereItemBau(vNome, vItem1 + "\n");
								oGrava.mConfiguraAtributos(vNome, vpch, vpce, vpcxp, vpclvl, vpcgold, vpcpot, vClasse, vMana);
								oGrava.mSalvar();	
								oDiz.mNovaFrase("# Item comprado com sucesso.");
								oDiz.mFala();
								oDiz.mPausa();
							}
						}
						
						if (vOptLojaItens.equals("2")) {
							if (vpcgold < vPrecoItem2) {
								oDiz.mNovaFrase("# Dinheiro insuficiente");
								oDiz.mFala();
								oDiz.mPausa();
							} else {
								// Subtrai Ouro e Adiciona Item.
								vpcgold = vpcgold - vPrecoItem2;
								oGrava.mInsereItemBau(vNome, vItem2 + "\n");
								oGrava.mConfiguraAtributos(vNome, vpch, vpce, vpcxp, vpclvl, vpcgold, vpcpot, vClasse, vMana);
								oGrava.mSalvar();	
								oDiz.mNovaFrase("# Item comprado com sucesso.");
								oDiz.mFala();
								oDiz.mPausa();
							}
						}
						
						if (vOptLojaItens.equals("3")) {
							if (vpcgold < vPrecoItem3) {
								oDiz.mNovaFrase("# Dinheiro insuficiente");
								oDiz.mFala();
								oDiz.mPausa();
							} else {
								// Subtrai Ouro e Adiciona Item.
								vpcgold = vpcgold - vPrecoItem3;
								oGrava.mInsereItemBau(vNome, vItem3 + "\n");
								oGrava.mConfiguraAtributos(vNome, vpch, vpce, vpcxp, vpclvl, vpcgold, vpcpot, vClasse, vMana);
								oGrava.mSalvar();	
								oDiz.mNovaFrase("# Item comprado com sucesso.");
								oDiz.mFala();
								oDiz.mPausa();
							}
						}
						
						if (vOptLojaItens.equals("4")) {
							if (vpcgold < vPrecoItem4) {
								oDiz.mNovaFrase("# Dinheiro insuficiente");
								oDiz.mFala();
								oDiz.mPausa();
							} else {
								// Subtrai Ouro e Adiciona Item.
								vpcgold = vpcgold - vPrecoItem4;
								oGrava.mInsereItemBau(vNome, vItem4 + "\n");
								oGrava.mConfiguraAtributos(vNome, vpch, vpce, vpcxp, vpclvl, vpcgold, vpcpot, vClasse, vMana);
								oGrava.mSalvar();	
								oDiz.mNovaFrase("# Item comprado com sucesso.");
								oDiz.mFala();
								oDiz.mPausa();
							}
						}
						
						if (vOptLojaItens.equals("X")) {
							vExitLojaItens=1;
						}
							
					}
						vExitLojaItens=0;
				}
				
				
				/*
					Processa Arena
				*/
				if (vOptMenu.equals("7")) {
				
					// Variáveis de Batalha
					Integer vPCAtk = 0;
					Integer vNPCAtk = 0;
					Integer vPCDef = 0;
					Integer vNPCDef = 0;
					Integer vPCDano = 0;
					Integer vNPCDano= 0;
					Integer vBonusAtk=Integer.valueOf(oGrava.mCarregaItemEquipado(vNome, "Arma", "2"));
					Integer vBonusDef=Integer.valueOf(oGrava.mCarregaItemEquipado(vNome, "Set", "2"));
					Integer vBonusDano=Integer.valueOf(oGrava.mCarregaItemEquipado(vNome, "Arma", "3"));
					Integer vBonusAbs=Integer.valueOf(oGrava.mCarregaItemEquipado(vNome, "Set", "3"));
					
					Object Arena = new Object();
					
					// Verifica Level PC
					vpclvl=Integer.parseInt(oGrava.mCarregar(vNome,4));
										
					// Gera Valores NPC
					vNomeNPC=oGrava.mGeraNomeOrc(oArena.mDados(1,20));
					vnpch = oArena.mNpcH(vpclvl);
					vnpce = oArena.mNpcE(vpclvl);
					vnpcgold = oArena.mNpcXP(vpclvl);
					vnpcxp = oArena.mNpcGold(vpclvl);
				
					synchronized(Arena) {
						while (vExitArena==0) 
						{
							// Mostra Opções da Arena
							// Atualiza Valores PC
							vpch=Integer.parseInt(oGrava.mCarregar(vNome,1));
							vpce=Integer.parseInt(oGrava.mCarregar(vNome,2));
							vpcxp=Integer.parseInt(oGrava.mCarregar(vNome,3));
							vpclvl=Integer.parseInt(oGrava.mCarregar(vNome,4));
							vpcgold=Integer.parseInt(oGrava.mCarregar(vNome,5));
							vpcpot=Integer.parseInt(oGrava.mCarregar(vNome,6));
							vClasse=oGrava.mCarregar(vNome,7);
							vMana=Integer.parseInt(oGrava.mCarregar(vNome,8));
							
							
							oDiz.mLimpa();
							oDiz.mNovaFrase("#");
							oDiz.mFala();
							oDiz.mNovaFrase("# ARENA");
							oDiz.mFala();
							oDiz.mNovaFrase("# "+ vNome +" - H: " + vpch + " E: " + vpce + " Gold: " + vpcgold + " LVL/XP " + vpclvl + "/" + vpcxp);
							oDiz.mFala();
							oDiz.mNovaFrase("#");
							oDiz.mFala();
							oDiz.mNovaFrase("# "+ vNomeNPC +" - H: " + vnpch + " E: " + vnpce + " Gold: " + vnpcgold);
							oDiz.mFala();
							oDiz.mNovaFrase("#");
							oDiz.mFala();						
							oDiz.mNovaFrase("#");
							oDiz.mFala();
							oDiz.mNovaFrase("# [1] Atacar");
							oDiz.mFala();
							oDiz.mNovaFrase("# [2] Rampage");
							oDiz.mFala();
							oDiz.mNovaFrase("#");
							oDiz.mFala();						
							oDiz.mNovaFrase("# [X] Sair");
							oDiz.mFala();
							vOptArena = oDiz.mPergunta();
							
							/*
								Processa Opções da Arena
							*/
							if (vOptArena.equals("1")) {
							
							if (vnpce < 1 || vpce < 1)
							{
							
								if (vnpce < 1) {
									oDiz.mNovaFrase("# Gerando Novo NPC");
									oDiz.mFala();
									oDiz.mWait();
									vNomeNPC=oGrava.mGeraNomeOrc(oArena.mDados(1,20));
									vnpch = oArena.mNpcH(vpclvl);
									vnpce = oArena.mNpcE(vpclvl);
									vnpcgold = oArena.mNpcXP(vpclvl);
									vnpcxp = oArena.mNpcGold(vpclvl);
									}
									
								if (vpce < 1) {
									oDiz.mNovaFrase("# Sem pontos de Vida, Vá beber!");
									oDiz.mFala();
									}
									
							} else {
							
									oDiz.mNovaFrase("# Processa Ataque de " + vNome);
									oDiz.mFala();
									oDiz.mWait();
									
									// Round PC
									vPCAtk = vpch + oArena.mDados(2,12) + vBonusAtk;
									vNPCDef = vnpch + oArena.mDados(2,12);
									oDiz.mNovaFrase("# Força de Ataque: " + vPCAtk);
									oDiz.mFala();
									oDiz.mWait();
									
									oDiz.mNovaFrase("# Força de Defesa: " + vNPCDef);
									oDiz.mFala();
									oDiz.mWait();
									
									if (vPCAtk > vNPCDef) {
										oDiz.mNovaFrase("# Ataque bem sucedido!");
										oDiz.mFala();
										oDiz.mWait();
										
										// Calcula Dano
										vPCDano = oArena.mDados(1,6) + vBonusDano;
										oDiz.mNovaFrase("# " + vNomeNPC + " sofre " + vPCDano + " pontos de dano.");
										oDiz.mFala();
										oDiz.mWait();
										
										vnpce = vnpce - vPCDano;
										
										if (vnpce < 1) {
											oDiz.mNovaFrase("# " + vNomeNPC + " foi destruído");
											oDiz.mFala();
											// Configura Gold e XP
											vpcgold = vpcgold + vnpcgold;
											vpcxp = vpcxp + vnpcxp;
											// Calcula Level
											vpclvl=oGrava.mConfiguraLevel(vpcxp);
											vpch=oGrava.mCalculaHabilidade(vpclvl);
											oGrava.mConfiguraAtributos(vNome, vpch, vpce, vpcxp, vpclvl, vpcgold, vpcpot, vClasse, vMana);
											oGrava.mSalvar();	
											oDiz.mPausa();
										} 
										
									} else {
										oDiz.mNovaFrase("# " + vNomeNPC + " Defendeu!");
										oDiz.mFala();
										oDiz.mWait();
									}
									
									if (vnpce > 0) {
										// Round NPC
										oDiz.mNovaFrase("# Ataque de " + vNomeNPC + "!");
										oDiz.mFala();
										oDiz.mWait();
										vNPCAtk = vnpch + oArena.mDados(2,12);
										vPCDef = vpch + oArena.mDados(2,12) + vBonusDef;
										oDiz.mNovaFrase("# Força de Ataque: " + vNPCAtk);
										oDiz.mFala();
										oDiz.mWait();
										
										oDiz.mNovaFrase("# Força de Defesa: " + vPCDef);
										oDiz.mFala();
										oDiz.mWait();
										
										if (vNPCAtk > vPCDef) {
											oDiz.mNovaFrase("# Ataque bem sucedido!");
											oDiz.mFala();
											oDiz.mWait();
											
											// Calcula Dano
											vNPCDano = oArena.mDados(1,6) - vBonusAbs;
											oDiz.mNovaFrase("# " + vNome + " sofre " + vNPCDano + " pontos de dano.");
											oDiz.mFala();
											oDiz.mWait();
											
											vpce = vpce - vNPCDano;
											oGrava.mConfiguraAtributos(vNome, vpch, vpce, vpcxp, vpclvl, vpcgold, vpcpot, vClasse, vMana);
											oGrava.mSalvar();	
											
											// Processa Poções
											while (vpce < 1 && vpcpot > 0)
											{
												
												vpce = vpce + oArena.mDados(2,12);
												vpcpot = vpcpot - 1;
												oGrava.mConfiguraAtributos(vNome, vpch, vpce, vpcxp, vpclvl, vpcgold, vpcpot, vClasse, vMana);
												oGrava.mSalvar();	
												oDiz.mNovaFrase("# Recuperando Energia");
												oDiz.mFala();
												oDiz.mWait();
											}
											
											if (vpce < 1 && vpcpot < 1) {
												oDiz.mNovaFrase("# " + vNome + "foi humilhado.");
												oDiz.mFala();
												oDiz.mPausa();
											}
											
										} else {
											oDiz.mNovaFrase("# " + vNome + " Defendeu!");
											oDiz.mFala();
											oDiz.mWait();
										}
									}
									
									
								}
									
								
							}
							
							
							// Verifica Opção Rampage
							if (vOptArena.equals("2")) {
							
							if (vnpce < 1 || vpce < 1)
							{
							
								if (vnpce < 1) {
									oDiz.mNovaFrase("# Gerando Novo NPC");
									oDiz.mFala();
									oDiz.mWait();
									vNomeNPC=oGrava.mGeraNomeOrc(oArena.mDados(1,20));
									vnpch = oArena.mNpcH(vpclvl);
									vnpce = oArena.mNpcE(vpclvl);
									vnpcgold = oArena.mNpcXP(vpclvl);
									vnpcxp = oArena.mNpcGold(vpclvl);
									}
									
								if (vpce < 1) {
									oDiz.mNovaFrase("# Sem pontos de Vida, Vá beber!");
									oDiz.mFala();
									}
									
							} else {
							
							
								oDiz.mNovaFrase("# RAAAMPAGEEE!");
								oDiz.mFala();
								oDiz.mWait();
							
								while (vnpce > 0 && vpce > 0)
								{
								if (vnpce < 1 || vpce < 1)
								{
								
									if (vnpce < 1) {
										oDiz.mNovaFrase("# Gerando Novo NPC");
										oDiz.mFala();
										oDiz.mWait();
										vNomeNPC=oGrava.mGeraNomeOrc(oArena.mDados(1,20));
										vnpch = oArena.mNpcH(vpclvl);
										vnpce = oArena.mNpcE(vpclvl);
										vnpcgold = oArena.mNpcXP(vpclvl);
										vnpcxp = oArena.mNpcGold(vpclvl);
										}
										
									if (vpce < 1) {
										oDiz.mNovaFrase("# Sem pontos de Vida, Vá beber!");
										oDiz.mFala();
										}
										
								} else {
								
										//oDiz.mNovaFrase("# Processa Ataque de " + vNome);
										//oDiz.mFala();
										//oDiz.mWait();
										
										// Round PC
										vPCAtk = vpch + oArena.mDados(2,12) + vBonusAtk;
										vNPCDef = vnpch + oArena.mDados(2,12);
										//oDiz.mNovaFrase("# Força de Ataque: " + vPCAtk);
										//oDiz.mFala();
										//oDiz.mWait();
										
										//oDiz.mNovaFrase("# Força de Defesa: " + vNPCDef);
										//oDiz.mFala();
										//oDiz.mWait();
										
										if (vPCAtk > vNPCDef) {
											//oDiz.mNovaFrase("# Ataque bem sucedido!");
											//oDiz.mFala();
											//oDiz.mWait();
											
											// Calcula Dano
											vPCDano = oArena.mDados(1,6) + vBonusDano;
											oDiz.mNovaFrase("# " + vNomeNPC + " sofre " + vPCDano + " pontos de dano.");
											oDiz.mFala();
											oDiz.mWait();
											
											vnpce = vnpce - vPCDano;
											
											if (vnpce < 1) {
												oDiz.mNovaFrase("# " + vNomeNPC + " foi destruído");
												// Configura Gold e XP
												vpcgold = vpcgold + vnpcgold;
												vpcxp = vpcxp + vnpcxp;
												// Calcula Level e Reprocessa Atributos
												vpclvl=oGrava.mConfiguraLevel(vpcxp);
												vpch=oGrava.mCalculaHabilidade(vpclvl);
												oDiz.mFala();
												oGrava.mConfiguraAtributos(vNome, vpch, vpce, vpcxp, vpclvl, vpcgold, vpcpot, vClasse, vMana);
												oGrava.mSalvar();	
												oDiz.mPausa();
											} 
											
										} else {
											//oDiz.mNovaFrase("# " + vNomeNPC + " Defendeu!");
											//oDiz.mFala();
											//oDiz.mWait();
										}
										
										if (vnpce > 0) {
											// Round NPC
											//oDiz.mNovaFrase("# Ataque de " + vNomeNPC + "!");
											//oDiz.mFala();
											//oDiz.mWait();
											vNPCAtk = vnpch + oArena.mDados(2,12);
											vPCDef = vpch + oArena.mDados(2,12) + vBonusDef;
											//oDiz.mNovaFrase("# Força de Ataque: " + vNPCAtk);
											//oDiz.mFala();
											//oDiz.mWait();
											
											//oDiz.mNovaFrase("# Força de Defesa: " + vPCDef);
											//oDiz.mFala();
											//oDiz.mWait();
											
											if (vNPCAtk > vPCDef) {
												
												//oDiz.mNovaFrase("# Ataque bem sucedido!");
												//oDiz.mFala();
												//oDiz.mWait();
												
												// Calcula Dano
												vNPCDano = oArena.mDados(1,6) - vBonusAbs;
												oDiz.mNovaFrase("# " + vNome + " sofre " + vNPCDano + " pontos de dano.");
												oDiz.mFala();
												oDiz.mWait();
												
												vpce = vpce - vNPCDano;
												oGrava.mConfiguraAtributos(vNome, vpch, vpce, vpcxp, vpclvl, vpcgold, vpcpot, vClasse, vMana);
												oGrava.mSalvar();	
												// Processa Poções
												while (vpce < 1 && vpcpot > 0)
												{
													
													vpce = vpce + oArena.mDados(2,12);
													vpcpot = vpcpot - 1;
													oGrava.mConfiguraAtributos(vNome, vpch, vpce, vpcxp, vpclvl, vpcgold, vpcpot, vClasse, vMana);
													oGrava.mSalvar();	
													//oDiz.mNovaFrase("# Recuperando Energia");
													//oDiz.mFala();
													//oDiz.mWait();
												}
												
												if (vpce < 1 && vpcpot < 1) {
													oDiz.mNovaFrase("# " + vNome + "foi humilhado. tsc tsc ¬¬");
													oDiz.mFala();
													oDiz.mPausa();
												}
												
											} else {
												//oDiz.mNovaFrase("# " + vNome + " Defendeu!");
												//oDiz.mFala();
												//oDiz.mWait();
										}
										}
										
										
									}
									}
								}
									
								
							}
							
							if (vOptArena.equals("X")) {
								vExitArena=1;
							}
								
						}
							vExitArena=0;
					}
				}
			
			
			} 
				vExitMenu=0;
			
		} while (vExit==0);
		
		}
	}
