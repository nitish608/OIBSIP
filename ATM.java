import java.util.Scanner;

class ATM {
    private int[] userIds;
    private int[] pins;
    private int[] balances;
    private int currentUserId;
    private String[] transactionHistory;

    public ATM() {
        userIds = new int[]{123456, 987654}; // Sample user IDs
        pins = new int[]{1234, 4321}; // Sample PINs
        balances = new int[]{1000, 500}; // Sample account balances
        transactionHistory = new String[userIds.length];
        for (int i = 0; i < transactionHistory.length; i++) {
            transactionHistory[i] = "";
        }
    }

    public boolean login(int userId, int pin) {
    for (int i = 0; i < userIds.length; i++) {
        if (userIds[i] == userId && pins[i] == pin) {
            currentUserId = userId;
            return true;
        }
    }
    throw new IllegalArgumentException("Invalid user ID or PIN.");
    }


    public void displayMenu() {
        System.out.println("ATM Menu");
        System.out.println("1. Transactions History");
        System.out.println("2. Withdraw");
        System.out.println("3. Deposit");
        System.out.println("4. Transfer");
        System.out.println("5. Quit");
        System.out.println();
    }

    public void showTransactionsHistory() {
        System.out.println("Transactions History:");
        int currentUserIndex = getUserIndex(currentUserId);

        if (currentUserIndex != -1) {
            String history = transactionHistory[currentUserIndex];
            if (history.isEmpty()) {
                System.out.println("No transactions found.");
            } else {
                System.out.println(history);
            }
        } else {
            System.out.println("Invalid User ID. No transaction history found.");
        }

        System.out.println();
    }
    
    private int getUserIndex(int userId) {
        for (int i = 0; i < userIds.length; i++) {
            if (userIds[i] == userId) {
                return i;
            }
        }
        return -1;
    }

    private int getUserBalance(int userIndex) {
        return balances[userIndex];
    }

    private void updateUserBalance(int userIndex, int newBalance) {
        balances[userIndex] = newBalance;
    }

    private void addToTransactionHistory(int userId, String transaction) {
        int userIndex = getUserIndex(userId);
        if (userIndex != -1) {
            transactionHistory[userIndex] += transaction + "\n";
        }
    }

    public void withdraw(int amount) {
        int currentUserIndex = getUserIndex(currentUserId);
        int currentBalance = getUserBalance(currentUserIndex);

        if (amount > 0) {
            if (currentBalance >= amount) {
                currentBalance -= amount;
                updateUserBalance(currentUserIndex, currentBalance);

                String transaction = "Withdrawal of $" + amount + " successful.";
                addToTransactionHistory(currentUserId, transaction);
                System.out.println(transaction);
            } else {
                System.out.println("Insufficient funds. Withdrawal cannot be completed.");
            }
        } else {
            System.out.println("Invalid amount. Please try again.");
        }

        System.out.println();
    }

    public void deposit(int amount) {
        int currentUserIndex = getUserIndex(currentUserId);
        int currentBalance = getUserBalance(currentUserIndex);

        if (amount > 0) {
            currentBalance += amount;
            updateUserBalance(currentUserIndex, currentBalance);

            String transaction = "Deposit of $" + amount + " successful.";
            addToTransactionHistory(currentUserId, transaction);
            System.out.println(transaction);
        } else {
            System.out.println("Invalid amount. Please try again.");
        }

        System.out.println();
    }

    public void transfer(int amount, int recipientId) {
        if (amount > 0) {
            int currentUserIndex = getUserIndex(currentUserId);
            int recipientIndex = getUserIndex(recipientId);

            if (recipientIndex != -1) {
                int currentUserBalance = getUserBalance(currentUserIndex);
                if (currentUserBalance >= amount) {
                    currentUserBalance -= amount;
                    updateUserBalance(currentUserIndex, currentUserBalance);

                    int recipientBalance = getUserBalance(recipientIndex);
                    recipientBalance += amount;
                    updateUserBalance(recipientIndex, recipientBalance);

                    String transaction = "Transfer of $" + amount + " to user " + recipientId + " successful.";
                    addToTransactionHistory(currentUserId, transaction);
                    System.out.println(transaction);
                } else {
                    System.out.println("Insufficient funds. Transfer cannot be completed.");
                }
            } else {
                System.out.println("Recipient user does not exist. Please try again.");
            }
        } else {
            System.out.println("Invalid amount. Please try again.");
        }

        System.out.println();
    }

    public static void main(String[] args) {
        ATM atm = new ATM();
        Scanner scanner = new Scanner(System.in);
        int userId;
        int pin;

        System.out.print("Enter User ID: ");
        userId = scanner.nextInt();

        System.out.print("Enter User PIN: ");
        pin = scanner.nextInt();

        if (atm.login(userId, pin)) {
            int choice;

            do {
                atm.displayMenu();
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        atm.showTransactionsHistory();
                        break;
                    case 2:
                        System.out.print("Enter the withdrawal amount: ");
                        int withdrawalAmount = scanner.nextInt();
                        atm.withdraw(withdrawalAmount);
                        break;
                    case 3:
                        System.out.print("Enter the deposit amount: ");
                        int depositAmount = scanner.nextInt();
                        atm.deposit(depositAmount);
                        break;
                    case 4:
                        System.out.print("Enter the transfer amount: ");
                        int transferAmount = scanner.nextInt();
                        System.out.print("Enter the recipient User ID: ");
                        int recipientId = scanner.nextInt();
                        atm.transfer(transferAmount, recipientId);
                        break;
                    case 5:
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } while (choice != 5);
        } else {
            System.out.println("Invalid User ID or PIN. Access denied.");
        }

        scanner.close();
    }
}
