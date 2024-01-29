/**
 * Version 1.0 d'une appli bancaire simplifiée offrant la possibilitée de créer des clients, des comptes bancaires associés et des opérations ou
 * transactions bancaires sur ceux-ci telles que : versement, retrait ou virement 
 * + permet d'afficher l'historique des transactions sur un compte
 * + la gestion des cas particuliers est rudimentaire ici puisque la notion d'exception n'a pas encore été abordée
 * 
 * @author El babili - 2023
 * 
 */

package fr.fms;

import java.util.Date;
import java.util.Scanner;

import fr.fms.business.IBankImpl;
import fr.fms.entities.Account;
import fr.fms.entities.Current;
import fr.fms.entities.Customer;
import fr.fms.entities.Saving;
import fr.fms.entities.Transaction;

public class MyBankApp {
	public static void main(String[] args) {
		IBankImpl bankJob = new IBankImpl();
		Scanner scan = new Scanner(System.in);
		// Création de deux clients
		Customer robert = new Customer(1, "dupont", "robert", "robert.dupont@xmail.com");
		Customer julie = new Customer(2, "jolie", "julie", "julie.jolie@xmail.com");
		// Création d'un compte pour chaque clients
		Current firstAccount = new Current(100200300, new Date(), 1500, 200, robert);
		Saving secondAccount = new Saving(200300400, new Date(), 2000, 5.5, julie);
		// Ajout des comptes à la banque
		bankJob.addAccount(firstAccount);
		bankJob.addAccount(secondAccount);

		System.out.println("Bienvenue sur MaBank");
		System.out.println("saisissez un numéro de compte bancaire valide:");

	
//		long customerAccountNumber = 100200300;
//		Customer customerName = bankJob.consultAccount(customerAccountNumber).getCustomer();
		long customerAccountNumber = 0;
		Account customerAccount;
		while(true) {
			try {
				customerAccountNumber = Integer.parseInt(scan.nextLine());
				customerAccount = bankJob.consultAccount(customerAccountNumber);
				if(customerAccount != null) {
				String customerName = bankJob.consultAccount(customerAccountNumber).getCustomer().getFirstName();
//					String customerName = "fdfd";
					System.out.println("Bienvenue " + customerName + ", que souhaitez vous faire ? taper le numéro correspondant");
					int counter = 0;
					while (true) {
			
						System.out.println(
								"1:versement - 2: retrait - 3 virement - 4:information sur ce compte - 5 liste des opérations - 6:sortir");
						System.out.println(
								((counter != 0) ? "--------------------taper le numéro correspondant--------------------" : ""));
						int customerChoice = Integer.parseInt(scan.nextLine());
			
						switch (customerChoice) {
			
						case 1:
							counter++;
							System.out.println("saisissez le montant à verser sur ce compte:");
							double amountToAdd = Integer.parseInt(scan.nextLine());
							bankJob.pay(customerAccountNumber,amountToAdd);
							break;
						case 2:
							counter++;
							System.out.println("saisissez le montant à retirer sur ce compte:");
							double amountToSub = Integer.parseInt(scan.nextLine());
							bankJob.withdraw(customerAccountNumber, amountToSub);
							break;
						case 3:
							counter++;
							System.out.println("Saisissez le numero de compte destinataire:");
							long destAccountNumber = Integer.parseInt(scan.nextLine());
							System.out.println("Saisissez le montant à virer sur ce compte:");
							double amountToGive = Integer.parseInt(scan.nextLine());
							bankJob.transfert(customerAccountNumber, destAccountNumber, amountToGive);
							break;
						case 4:
							counter++;
							System.out.println(customerAccount);
			
							break;
						case 5:
							counter++;
							System.out.println("Liste des opérations");
							for(Transaction trans : bankJob.listTransactions(customerAccountNumber))
								System.out.println(trans);
							break;
						case 6:
							counter++;
							System.out.println("Sortie");
							break;
						default:
							counter++;
							System.out.println("Aucun choix ne correspond à votre demande");
							break;
						}
					}
				}
			}catch (Exception e) {
				System.out.println("saisissez un numéro de compte bancaire valide:");
			}
		}
		


	}
}
