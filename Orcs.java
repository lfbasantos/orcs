/*

 Orcs - Alpha v.1.4
 Luiz Filipe - lfbasantos@gmail.com
 
*/


/*
 Documentação
 
 Localização dos Atributos
 Habilidade => vpch => 1
 Energia => vpce => 2
 XP => vpcxp => 3
 Level => vpclvl => 4
 Gold => vpcgold => 5
 Poções => vpcpot => 6
 
*/


import java.io.*;
import java.math.*;
import java.math.BigInteger;
import java.util.*;
import java.lang.*;


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
			String vInput = console.readLine("Digite qualquer tecla para continuar...");
			return vInput;
		}
		
		void mLimpa() {
			for (int i = 0; i < 50; ++i) System.out.print("\n");
		}
		
		void mWait() {
			try { Thread.sleep(1000); } catch(InterruptedException e) {};
		}
		
		
	}

	class cSalvaCarrega {
		
		String vNome = "";
		String vItem = "";
		String vClasse = "";
		Integer vpch;
		Integer vpce;
		Integer	vpcxp; 
		Integer vpclvl;
		Integer vpcgold;
		Integer vpcpot;
		int vPos;
		
		void mConfiguraAtributos(String pNome, Integer ppch, Integer ppce, Integer ppcxp, Integer ppclvl, Integer ppcgold, Integer ppcpot, String pClasse) {
			vNome=pNome;
			vClasse=pClasse;
			vpch=ppch;
			vpce=ppce;
			vpcxp=ppcxp;
			vpclvl=ppclvl;
			vpcgold=ppcgold;
			vpcpot=ppcpot;
		}

		public void mSalvar() {	
			try {
				File vArq = new File("dat/"+vNome+".gamesave");
				vArq.createNewFile();
				FileWriter vSalvar = new FileWriter(vArq);
				vSalvar.write(vpch+";"+vpce+";"+vpcxp+";"+vpclvl+";"+vpcgold+";"+vpcpot+";"+vClasse);
				vSalvar.flush();
				vSalvar.close();
			} catch(IOException e)
			{
				e.printStackTrace();
			}
		}

		public void mInicializaBau(String pNome) {	
			vNome=pNome;
			String vItem1="1;Arma;1;1;100;1;Faca Pequena Atk 1 Dano 1 $100\n";
			String vItem2="2;Set;0;0;50;1;Roupas Simples Def 0 Absorve 0 $50\n";
			try {
				File vArq = new File("dat/"+vNome+".items");
				vArq.createNewFile();
				FileWriter vSalvar = new FileWriter(vArq);
				vSalvar.write("1;Arma;1;1;100;1;Faca Pequena Atk 1 Dano 1 $100\n");
				vSalvar.write("2;Set;0;0;50;1;Roupas Simples Def 0 Absorve 0 $50\n");
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
				File vArq = new File("dat/"+vNome+".items");
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
				Scanner vRegistro = new Scanner(new FileReader("dat/"+vNome+".gamesave"));
				vRegistro.useDelimiter(";");
				/*
					Processa Lista para Obter Valor 
				*/
				while (vRegistro.hasNext()) 
				{
					String vStr = vRegistro.next();
						if ("DONE".equals(vStr)) 
						{
							break;
						}
					if ( vCont == vPos ){
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
				Scanner vRegistro = new Scanner(new FileReader("dat/"+vNome+".items"));
				vRegistro.useDelimiter("\\n");
				/*
					Processa Registros
				*/
				while (vRegistro.hasNext()) 
				{
					String vStr = vRegistro.next();
						if ("DONE".equals(vStr)) 
						{
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
				Scanner vRegistro = new Scanner(new FileReader("dat/"+vNome+".items"));
				vRegistro.useDelimiter("\\n");
				/*
					Processa Registros
				*/
				while (vRegistro.hasNext()) 
				{
					String vStr = vRegistro.next();
						if ("DONE".equals(vStr)) 
						{
							return vExit;
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
							return vNomeItem;
						}
						
						if (vParam.equals("2")){
							oDiz.mNovaFrase("# Bônus 1: " + vBonus1);
							oDiz.mFala();
							return vBonus1;
						}
						
						if (vParam.equals("3")){
							oDiz.mNovaFrase("# Bônus 2: " + vBonus2);
							oDiz.mFala();
							return vBonus2;
						}
						
						vEquipado="0";
						vTipoItem="";
					} 
					
					vID++;
							
				}
			} catch(IOException e)
			{
				oDiz.mNovaFrase("Baú Não Encontrado.");
				oDiz.mFala();
				oDiz.mPausa();
				return vErro;
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
			Integer vExitOK=0;
			Integer vExit=0;
			cInterage oDiz = new cInterage();

			try {
				
				// Abre Arquivo Temporário
				File vArq = new File("dat/"+vNome+".items.temp");
				File vArq2 = new File("dat/"+vNome+".items");
				vArq.createNewFile();
				FileWriter vSalvar = new FileWriter(vArq);
		
				Scanner vRegistro = new Scanner(new FileReader("dat/"+vNome+".items"));
				vRegistro.useDelimiter("\\n");
				/*
					Processa Registros
				*/
				while (vRegistro.hasNext()) 
				{
					String vStr = vRegistro.next();
						if ("DONE".equals(vStr)) 
						{
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
				vSalvar.flush();
				vSalvar.close();
				//Rename do arquivo temporário para o novo arquivo.
				boolean vSucesso = vArq.renameTo(vArq2);
				
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
			Integer vExitOK=0;
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
				File vArq = new File("dat/"+vNome+".items.temp");
				File vArq2 = new File("dat/"+vNome+".items");
				vArq.createNewFile();
				FileWriter vSalvar = new FileWriter(vArq);
				
				// Verifica o Tipo e a Classe do Item
				Scanner vRegistro = new Scanner(new FileReader("dat/"+vNome+".items"));
				vRegistro.useDelimiter("\\n");
				while (vRegistro.hasNext()) 
				{
					String vStr = vRegistro.next();
						if ("DONE".equals(vStr)) 
						{
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
				
				
				// Atualiza Flag para Zero para todos os Itens do Tipo Selecionado
				Scanner vRegistro2 = new Scanner(new FileReader("dat/"+vNome+".items"));
				vRegistro2.useDelimiter("\\n");
				vIDRegistro=1;
				while (vRegistro2.hasNext()) 
				{
					String vStr2 = vRegistro2.next();
						if ("DONE".equals(vStr2)) 
						{
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
				
				vSalvar.close();
				boolean vSucesso = vArq.renameTo(vArq2);
				
						if (vDebug==1) {
								oDiz.mNovaFrase("FLAGS ATUALIZADOS PRA ZERO");
								oDiz.mFala();
								oDiz.mPausa();
							}
				
				// Atualiza Item para Flag 1
				vIDRegistro=1;
				File vArq3 = new File("dat/"+vNome+".items.temp");
				File vArq4 = new File("dat/"+vNome+".items");
				vArq3.createNewFile();
				FileWriter vSalvar2 = new FileWriter(vArq3);
				Scanner vRegistro3 = new Scanner(new FileReader("dat/"+vNome+".items"));
				vRegistro3.useDelimiter("\\n");
				while (vRegistro3.hasNext()) 
				{
					String vStr3 = vRegistro3.next();
						if ("DONE".equals(vStr3)) 
						{
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
				boolean vSucesso2 = vArq3.renameTo(vArq4);
				
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
	}


	public class Orcs {

		public static void main(String[] args) {
			/*
				Variáveis de PC e NPC
			*/
			String vOpt, vOptMenu, vOptLojaItens, vOptLojaArmas, vOptLojaMagia, vOptArena, vOptMissoes;
			String vNome = "";
			String vClasse ="";
			Integer vpch=0;
			Integer vpce=0;
			Integer vpcxp=0;
			Integer vpclvl=0;
			Integer vpcgold=0;
			Integer vpcpot=0;
			Integer vnpch=0;
			Integer vnpce=0;
			Integer vnpcxp=0;
			Integer vnpclvl=0;
			Integer vnpcgold=0;
			Integer vnpcpot=0;
			String vVerificaLoad="0";
			int vInicioOk=1;
			int vMenuOk=1;
			int vExit=0;
			int vExitMenu=0;
			int vExitLojaItens=0;
			int vExitArena=0;
			String[] vItems = new String[1000];
			
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
			
			/*
				Tela Inicial
			*/
			oDiz.mNovaFrase("#");
			oDiz.mFala();
			oDiz.mNovaFrase("# ORCS 1.4");
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
				oDiz.mNovaFrase("# [1] Guerreiro");
				oDiz.mFala();
				oDiz.mNovaFrase("# Habilidade 12");
				oDiz.mFala();
				oDiz.mNovaFrase("# Energia 16");
				oDiz.mFala();
				oDiz.mNovaFrase("");
				oDiz.mFala();
				oDiz.mNovaFrase("# [2] Mago");
				oDiz.mFala();
				oDiz.mNovaFrase("# Habilidade 12");
				oDiz.mFala();
				oDiz.mNovaFrase("# Energia 12");
				oDiz.mFala();
				oDiz.mNovaFrase("# Mana 10");
				oDiz.mFala();
				vClasse = oDiz.mPergunta();
				
				/*
					Guerreiro
				*/
				if (vClasse.equals("1")) {
					oDiz.mLimpa();
					vpch=12;
					vpce=16;
					vpcpot=5;
					vpcxp=0;
					vpclvl=1;
					vpcgold=1000;
					oGrava.mConfiguraAtributos(vNome, vpch, vpce, vpcxp, vpclvl, vpcgold, vpcpot, vClasse);
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
					vpcpot=3;
					vpcxp=0;
					vpclvl=1;
					vpcgold=1000;
					oGrava.mConfiguraAtributos(vNome, vpch, vpce, vpcxp, vpclvl, vpcgold, vpcpot, vClasse);
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
				oDiz.mNovaFrase("# [8] Missões");
				oDiz.mFala();
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
					/*
						Desenha Ficha
					*/
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
					oDiz.mNovaFrase("#");
					oDiz.mFala();
					oDiz.mNovaFrase("# Poções: "+vpcpot);
					oDiz.mFala();
					oDiz.mNovaFrase("# Gold: "+vpcgold);
					oDiz.mFala();
					oDiz.mNovaFrase("#");
					oDiz.mFala();
					oDiz.mNovaFrase("# Arma/Magia:");
					oDiz.mFala();
					oGrava.mCarregaItemEquipado(vNome, "Arma", "1");
					oDiz.mNovaFrase("# Set/Manto: ");
					oDiz.mFala();
					oGrava.mCarregaItemEquipado(vNome, "Set", "1");
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
									oGrava.mConfiguraAtributos(vNome, vpch, vpce, vpcxp, vpclvl, vpcgold, vpcpot, vClasse);
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
								oGrava.mConfiguraAtributos(vNome, vpch, vpce, vpcxp, vpclvl, vpcgold, vpcpot, vClasse);
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
						oDiz.mNovaFrase("# [1] [10] Cerveja - Recupera 2 Pontos de HP");
						oDiz.mFala();
						oDiz.mNovaFrase("#");
						oDiz.mFala();
						oDiz.mNovaFrase("# [2] [50] Vinho - Recupera 5 Pontos de HP");
						oDiz.mFala();
						oDiz.mNovaFrase("#");
						oDiz.mFala();
						oDiz.mNovaFrase("# [3] [150] Chá de Cogumelo - Recupera 10 Pontos de HP");
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
								oGrava.mConfiguraAtributos(vNome, vpch, vpce, vpcxp, vpclvl, vpcgold, vpcpot, vClasse);
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
								oGrava.mConfiguraAtributos(vNome, vpch, vpce, vpcxp, vpclvl, vpcgold, vpcpot, vClasse);
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
								oGrava.mConfiguraAtributos(vNome, vpch, vpce, vpcxp, vpclvl, vpcgold, vpcpot, vClasse);
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
								oGrava.mConfiguraAtributos(vNome, vpch, vpce, vpcxp, vpclvl, vpcgold, vpcpot, vClasse);
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
								oGrava.mConfiguraAtributos(vNome, vpch, vpce, vpcxp, vpclvl, vpcgold, vpcpot, vClasse);
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
								oGrava.mConfiguraAtributos(vNome, vpch, vpce, vpcxp, vpclvl, vpcgold, vpcpot, vClasse);
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
								oGrava.mConfiguraAtributos(vNome, vpch, vpce, vpcxp, vpclvl, vpcgold, vpcpot, vClasse);
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
								oGrava.mConfiguraAtributos(vNome, vpch, vpce, vpcxp, vpclvl, vpcgold, vpcpot, vClasse);
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
								oGrava.mConfiguraAtributos(vNome, vpch, vpce, vpcxp, vpclvl, vpcgold, vpcpot, vClasse);
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
								oGrava.mConfiguraAtributos(vNome, vpch, vpce, vpcxp, vpclvl, vpcgold, vpcpot, vClasse);
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
								oGrava.mConfiguraAtributos(vNome, vpch, vpce, vpcxp, vpclvl, vpcgold, vpcpot, vClasse);
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
					Object Arena = new Object();
					
					// Gera Valores NPC
					vnpch = oArena.mDados(6,10);
					vnpce = oArena.mDados(6,14);
					vnpcgold = oArena.mDados(500,999);
				
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
							
							
							oDiz.mLimpa();
							oDiz.mNovaFrase("#");
							oDiz.mFala();
							oDiz.mNovaFrase("# ARENA");
							oDiz.mFala();
							oDiz.mNovaFrase("# PC - H: " + vpch + " E: " + vpce + "Gold: " + vpcgold);
							oDiz.mFala();
							oDiz.mNovaFrase("#");
							oDiz.mFala();
							oDiz.mNovaFrase("# NPC - H: " + vnpch + " E: " + vnpce + " Gold: " + vnpcgold);
							oDiz.mFala();
							oDiz.mNovaFrase("#");
							oDiz.mFala();						
							oDiz.mNovaFrase("#");
							oDiz.mFala();
							oDiz.mNovaFrase("# [1] Atacar");
							oDiz.mFala();
							oDiz.mNovaFrase("# [2] Rampage");
							oDiz.mFala();
							oDiz.mNovaFrase("# [3] Poções");
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
								
									oDiz.mNovaFrase("# Processa Ataque");
									oDiz.mFala();
									oDiz.mWait();
									
									// Round PC
									vPCAtk = vpch + oArena.mDados(2,12);
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
										vPCDano = oArena.mDados(1,6);
										oDiz.mNovaFrase("# ORC sofre " + vPCDano + " pontos de dano.");
										oDiz.mFala();
										oDiz.mWait();
										
										vnpce = vnpce - vPCDano;
										
										if (vnpce < 1) {
											oDiz.mNovaFrase("# ORC foi destruído");
											oDiz.mFala();
											oDiz.mPausa();
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
