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
		System.out.println("Saississez un numéro de compte bancaire valide:");

//		long customerAccountNumber = Integer.parseInt(scan.nextLine());

//		Customer customerName = bankJob.consultAccount(customerAccountNumber).getCustomer();
		String customerName = bankJob.consultAccount(100200300).getCustomer().getFirstName();
		Account askedAccount = bankJob.consultAccount(100200300);

		System.out.println("Bienvenue " + customerName + ", que souhaitez vous faire ? taper le numéro correspondant");
		int counter = 0;
		while (true) {

			System.out.println(
					"1:versement - 2: retrait - 3 virement - 4:information sur ce compte - 5 liste des opérations - 6:sortir");
			System.out.print(
					((counter != 0) ? "--------------------taper le numéro correspondant--------------------" : ""));
			int customerChoice = Integer.parseInt(scan.nextLine());

			switch (customerChoice) {

			case 1:
				counter++;
				System.out.println("Methode versement");
				break;
			case 2:
				counter++;
				System.out.println("Méthode retrait");
				break;
			case 3:
				counter++;
				System.out.println("Methode virement");
				break;
			case 4:
				counter++;
				System.out.println(askedAccount);

				break;
			case 5:
				counter++;
				System.out.println("Liste des opérations");
				break;
			case 6:
				counter++;
				System.out.println("Quitter");
				break;
			default:
				counter++;
				System.out.println("Aucun choix ne correspond à votre demande");
			}
		}

	}
}
