package owlmoney;

import owlmoney.model.profile.Profile;
import owlmoney.storage.Storage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import static owlmoney.commons.log.LogsCenter.getLogger;

/**
 * Generates dummy data and writes to CSV files for v1.4 test so that the application can import them.
 */
class MainImport {
    private Storage storage;
    private static final Logger logger = getLogger(Profile.class);
    private static final String FILE_PATH = "data/";
    private static final String PROFILE_FILE_NAME = "profile.csv";
    private static final String PROFILE_BANK_LIST_FILE_NAME = "profile_banklist.csv";
    private static final String PROFILE_GOAL_LIST_FILE_NAME = "profile_goallist.csv";
    private static final String PROFILE_CARD_LIST_FILE_NAME = "profile_cardlist.csv";
    private static final String INVESTMENT_BOND_LIST_FILE_NAME = "_investment_bondList.csv";
    private static final String INVESTMENT_TRANSACTION_LIST_FILE_NAME = "_investment_transactionList.csv";
    private static final String SAVING_TRANSACTION_LIST_FILE_NAME = "_saving_transactionList.csv";
    private static final String SAVING_RECURRING_TRANSACTION_LIST_FILE_NAME = "_saving_recurring_transactionList.csv";
    private static final String CARD_PAID_TRANSACTION_LIST_FILE_NAME = "_card_paid_transactionList.csv";
    private static final String CARD_UNPAID_TRANSACTION_LIST_FILE_NAME = "_card_unpaid_transactionList.csv";
    private static final String PROFILE_ACHIEVEMENT_LIST_FILE_NAME = "profile_achievementlist.csv";

    /**
     * Creates an instance of MainImport to mass import dummy data for testing purposes.
     */
    MainImport() {
        storage = new Storage(FILE_PATH);
    }

    /**
     * Creates all required dummy files for testing.
     */
    void createAllFiles() {
        try {
            storage.createDirectoryIfNotExist(FILE_PATH);
            createProfileFile();
            createBankListFile();
            createCardListFile();
            createSavingExpenditureListFile("0"); //savings account is bank 0 in bankList
            createSavingRecurringExpenditureListFile("0"); //savings account is bank 0 in bankList
            createInvestmentBondListFile("1"); //investment account is bank 1 in bankList
            createInvestmentTransactionListFile("1"); //investment account is bank 1 in bankList
            createCardUnpaidTransactionListFile("0"); //POSB card is card 0 in cardList
            createCardPaidTransactionListFile("0"); //POSB card is card 0 in cardList
            createGoalListFile();
            createAchievementListFile();
        } catch (IOException exceptionMessage) {
            logger.warning("Error creating dummy data.");
        }
    }

    /**
     * Prepares profile file data.
     *
     * @return arrayList that is ready to be written to the profile file.
     */
    private ArrayList<String[]> prepareProfile() {
        ArrayList<String[]> exportArrayList = new ArrayList<>();
        exportArrayList.add(new String[]{"Name"});
        exportArrayList.add(new String[]{"john"});
        return exportArrayList;
    }

    /**
     * Creates the profile file.
     *
     * @throws IOException If there are errors creating the profile file.
     */
    private void createProfileFile() throws IOException {
        ArrayList<String[]> inputData = prepareProfile();
        storage.writeFile(inputData,PROFILE_FILE_NAME);
    }

    /**
     * Prepares bank data for bankList.
     *
     * @return arrayList that is ready to be written to the bankList file.
     */
    private ArrayList<String[]> prepareBankData() {
        ArrayList<String[]> exportArrayList = new ArrayList<>();
        exportArrayList.add(new String[]{"accountName","type","amount","income","nextIncomeDate"});
        exportArrayList.add(new String[]{
            "MayBank Savings Account",
            "saving",
            "13889.75",
            "5000.00",
            "01/12/2019"});
        exportArrayList.add(new String[]{
            "DBS Vickers Account",
            "investment",
            "5456.91",
            ".00",
            ""});
        return exportArrayList;
    }

    /**
     * Creates the bank list file.
     *
     * @throws IOException If there are errors creating the bank list file.
     */
    private void createBankListFile() throws IOException {
        ArrayList<String[]> inputData = prepareBankData();
        storage.writeFile(inputData,PROFILE_BANK_LIST_FILE_NAME);
    }

    /**
     * Prepares transactions for savings account.
     *
     * @return arrayList that is ready to be written to the savings transaction list file.
     */
    private ArrayList<String[]> prepareSavingTransactions() {
        ArrayList<String[]> exportArrayList = new ArrayList<>();
        exportArrayList.add(new String[]{"description","amount","date","category","spent","cardId","billDate"});
        exportArrayList.add(new String[]{
            "Koi Bubble Tea",
            "3.5",
            "02/01/2019",
            "dining",
            "true",
            "",
            ""});
        exportArrayList.add(new String[]{
            "Chicken Rice",
            "4",
            "08/03/2019",
            "dining",
            "true",
            "",
            ""});
        exportArrayList.add(new String[]{
            "Ice Coffee",
            "7.5",
            "13/05/2019",
            "dining",
            "true",
            "",
            ""});
        exportArrayList.add(new String[]{
            "Uniqlo TShirt",
            "39.9",
            "09/08/2019",
            "shopping",
            "true",
            "",
            ""});
        exportArrayList.add(new String[]{
            "Anker Powerbank",
            "29.99",
            "09/09/2019",
            "Miscellaneous",
            "true",
            "",
            ""});
        exportArrayList.add(new String[]{
            "FREELANCE",
            "500",
            "29/10/2019",
            "deposit",
            "false",
            "",
            ""});
        exportArrayList.add(new String[]{
            "Pixel 4 XL",
            "1469.99",
            "24/10/2019",
            "Miscellaneous",
            "true",
            "",
            ""});
        exportArrayList.add(new String[]{
            "Ramen",
            "13.37",
            "25/10/2019",
            "dining",
            "true",
            "",
            ""});
        exportArrayList.add(new String[]{
            "Movie Joker",
            "13.5",
            "03/11/2019",
            "Miscellaneous",
            "true",
            "",
            ""});
        exportArrayList.add(new String[]{
            "Grab to NUS",
            "22",
            "04/11/2019",
            "transport",
            "true",
            "",
            ""});
        exportArrayList.add(new String[]{
            "Chicken Chop",
            "6.5",
            "07/11/2019",
            "dining",
            "true",
            "",
            ""});
        return exportArrayList;
    }

    /**
     * Creates savings transaction list file.
     *
     * @param bankNumber the prepend arrayList number of the bank in the bank list.
     * @throws IOException If there are errors creating the transaction list file.
     */
    private void createSavingExpenditureListFile(String bankNumber) throws IOException {
        ArrayList<String[]> inputData = prepareSavingTransactions();
        storage.writeFile(inputData,bankNumber + SAVING_TRANSACTION_LIST_FILE_NAME);
    }

    /**
     * Prepares recurring transaction records for savings account.
     *
     * @return arrayList that is ready to be written to the savings recurring transaction list file.
     */
    private ArrayList<String[]> prepareSavingRecurringTransactions() {
        ArrayList<String[]> exportArrayList = new ArrayList<>();
        exportArrayList.add(new String[]{"description","amount","date","category","spent"});
        exportArrayList.add(new String[]{
            "Singtel Bill",
            "62.9",
            "01/12/2019",
            "bills",
            "true"});
        return exportArrayList;
    }

    /**
     * Creates savings recurring transaction list file.
     *
     * @param bankNumber the prepend arrayList number of the bank in the bank list.
     * @throws IOException If there are errors creating the transaction list file.
     */
    private void createSavingRecurringExpenditureListFile(String bankNumber) throws IOException {
        ArrayList<String[]> inputData = prepareSavingRecurringTransactions();
        storage.writeFile(inputData,bankNumber + SAVING_RECURRING_TRANSACTION_LIST_FILE_NAME);
    }

    /**
     * Prepares investment bond records for investment account.
     *
     * @return arrayList that is ready to be written to the bond list file.
     */
    private ArrayList<String[]> prepareInvestmentBonds() {
        ArrayList<String[]> exportArrayList = new ArrayList<>();
        exportArrayList.add(new String[]{"bondName","amount","rate","boughtDate","year",
            "nextDateToCreditInterest","isMature"});
        exportArrayList.add(new String[]{
            "January SSB",
            "500.00",
            "2.01",
            "01/02/2019",
            "10",
            "01/02/2020",
            "false"});
        exportArrayList.add(new String[]{
            "February SSB",
            "1000.00",
            "1.98",
            "01/03/2019",
            "10",
            "01/03/2020",
            "false"});
        exportArrayList.add(new String[]{
            "March SSB",
            "3000.00",
            "1.95",
            "01/04/2019",
            "10",
            "01/04/2020",
            "false"});
        exportArrayList.add(new String[]{
            "April SSB",
            "1300.00",
            "1.96",
            "01/05/2019",
            "10",
            "01/05/2020",
            "false"});
        exportArrayList.add(new String[]{
            "May SSB",
            "2100.00",
            "1.95",
            "01/06/2019",
            "10",
            "01/12/2019",
            "false"});
        exportArrayList.add(new String[]{
            "June SSB",
            "2000.00",
            "1.88",
            "01/07/2019",
            "10",
            "01/01/2020",
            "false"});
        exportArrayList.add(new String[]{
            "July SSB",
            "1500.00",
            "1.93",
            "01/08/2019",
            "10",
            "01/02/2020",
            "false"});
        exportArrayList.add(new String[]{
            "August SSB",
            "1000.00",
            "1.68",
            "01/09/2019",
            "10",
            "01/03/2020",
            "false"});
        exportArrayList.add(new String[]{
            "September SSB",
            "1000.00",
            "1.65",
            "01/10/2019",
            "10",
            "01/04/2020",
            "false"});
        exportArrayList.add(new String[]{
            "October SSB",
            "1200.00",
            "1.62",
            "01/11/2019",
            "10",
            "01/05/2020",
            "false"});
        return exportArrayList;
    }

    /**
     * Creates investment bond list file.
     *
     * @param bankNumber the prepend arrayList number of the bank in the bank list.
     * @throws IOException If there are errors creating the bond list file.
     */
    private void createInvestmentBondListFile(String bankNumber) throws IOException {
        ArrayList<String[]> inputData = prepareInvestmentBonds();
        storage.writeFile(inputData,bankNumber + INVESTMENT_BOND_LIST_FILE_NAME);
    }

    /**
     * Prepares investment transaction records for investment account.
     *
     * @return arrayList that is ready to be written to the transaction list file.
     */
    private ArrayList<String[]> prepareInvestmentTransactionList() {
        ArrayList<String[]> exportArrayList = new ArrayList<>();
        exportArrayList.add(new String[]{"description","amount","date","category","spent","cardId","billDate"});
        exportArrayList.add(new String[]{
            "January SSB",
            "500",
            "01/02/2019",
            "bonds",
            "true",
            "",
            ""});
        exportArrayList.add(new String[]{
            "February SSB",
            "1000",
            "01/03/2019",
            "bonds",
            "true",
            "",
            ""});
        exportArrayList.add(new String[]{
            "March SSB",
            "3000",
            "01/04/2019",
            "bonds",
            "true",
            "",
            ""});
        exportArrayList.add(new String[]{
            "April SSB",
            "1300",
            "01/05/2019",
            "bonds",
            "true",
            "",
            ""});
        exportArrayList.add(new String[]{
            "May SSB",
            "2100",
            "01/06/2019",
            "bonds",
            "true",
            "",
            ""});
        exportArrayList.add(new String[]{
            "June SSB",
            "2000",
            "01/07/2019",
            "bonds",
            "true",
            "",
            ""});
        exportArrayList.add(new String[]{
            "July SSB",
            "1500",
            "01/08/2019",
            "bonds",
            "true",
            "",
            ""});
        exportArrayList.add(new String[]{
            "August SSB",
            "1000",
            "01/09/2019",
            "bonds",
            "true",
            "",
            ""});
        exportArrayList.add(new String[]{
            "September SSB",
            "1000",
            "01/10/2019",
            "bonds",
            "true",
            "",
            ""});
        exportArrayList.add(new String[]{
            "October SSB",
            "1200",
            "01/11/2019",
            "bonds",
            "true",
            "",
            ""});
        exportArrayList.add(new String[]{
            "January SSB",
            "5.02",
            "01/08/2019",
            "bonds",
            "false",
            "",
            ""});
        exportArrayList.add(new String[]{
            "February SSB",
            "9.9",
            "01/09/2019",
            "bonds",
            "false",
            "",
            ""});
        exportArrayList.add(new String[]{
            "March SSB",
            "29.25",
            "01/10/2019",
            "bonds",
            "false",
            "",
            ""});
        exportArrayList.add(new String[]{
            "April SSB",
            "12.74",
            "01/11/2019",
            "bonds",
            "false",
            "",
            ""});
        return exportArrayList;
    }

    /**
     * Creates investment transaction list file.
     *
     * @param bankNumber the prepend arrayList number of the bank in the bank list.
     * @throws IOException If there are errors creating the transaction list file.
     */
    private void createInvestmentTransactionListFile(String bankNumber) throws IOException {
        ArrayList<String[]> inputData = prepareInvestmentTransactionList();
        storage.writeFile(inputData,bankNumber + INVESTMENT_TRANSACTION_LIST_FILE_NAME);
    }

    /**
     * Prepares card records for card list.
     *
     * @return arrayList that is ready to be written to the card list file.
     */
    private ArrayList<String[]> prepareCardData() {
        ArrayList<String[]> exportArrayList = new ArrayList<>();
        exportArrayList.add(new String[] {"cardName", "cardLimit", "rebateRate", "uuid"});
        exportArrayList.add(new String[]{
            "POSB Everyday Card",
            "5000.00",
            "1.00",
            "812fc43a-bc2c-4eeb-b451-274dcd370edb"});
        exportArrayList.add(new String[]{
            "MayBank eVibes Card",
            "500.00",
            ".50",
            "e18f532c-99b7-4824-869a-03f1648cbb31"});
        return exportArrayList;
    }

    /**
     * Creates card list file.
     *
     * @throws IOException If there are errors creating the transaction list file.
     */
    private void createCardListFile() throws IOException {
        ArrayList<String[]> inputData = prepareCardData();
        storage.writeFile(inputData,PROFILE_CARD_LIST_FILE_NAME);
    }

    /**
     * Prepares unpaid transaction records for card transaction list.
     *
     * @return arrayList that is ready to be written to the card transaction list file.
     */
    private ArrayList<String[]> prepareCardUnpaidTransactionList() {
        ArrayList<String[]> exportArrayList = new ArrayList<>();
        exportArrayList.add(new String[]{"description","amount","date","category","cardId","billDate",});
        exportArrayList.add(new String[]{
            "Mix Vegetable Rice",
            "4.00",
            "01/11/2019",
            "dining",
            "",
            ""});
        exportArrayList.add(new String[]{
            "LiHo Bubble Tea",
            "5.20",
            "02/11/2019",
            "dining",
            "",
            ""});
        exportArrayList.add(new String[]{
            "Starbucks",
            "7.40",
            "05/11/2019",
            "dining",
            "",
            ""});
        exportArrayList.add(new String[]{
            "Popular Stationary",
            "2.10",
            "06/11/2019",
            "Miscellaneous",
            "",
            ""});
        exportArrayList.add(new String[]{
            "Movie Batman",
            "9.50",
            "08/11/2019",
            "Miscellaneous",
            "",
            ""});
        exportArrayList.add(new String[]{
            "Movie Terminator",
            "13.50",
            "09/11/2019",
            "Miscellaneous",
            "",
            ""});
        return exportArrayList;
    }

    /**
     * Creates the card unpaid transaction list file.
     *
     * @param cardNumber the prepend arrayList number of the card in the card list.
     * @throws IOException If there are errors creating the transaction list file.
     */
    private void createCardUnpaidTransactionListFile(String cardNumber) throws IOException {
        ArrayList<String[]> inputData = prepareCardUnpaidTransactionList();
        storage.writeFile(inputData,cardNumber + CARD_UNPAID_TRANSACTION_LIST_FILE_NAME);
    }

    /**
     * Prepares paid transaction records for card transaction list.
     *
     * @return arrayList that is ready to be written to the card transaction list file.
     */
    private ArrayList<String[]> prepareCardpaidTransactionList() {
        ArrayList<String[]> exportArrayList = new ArrayList<>();
        exportArrayList.add(new String[] {"description", "amount", "date", "category", "cardId", "billDate",});
        return exportArrayList;
    }

    /**
     * Creates the card paid transaction list file.
     *
     * @param cardNumber the prepend arrayList number of the card in the card list.
     * @throws IOException If there are errors creating the transaction list file.
     */
    private void createCardPaidTransactionListFile(String cardNumber) throws IOException {
        ArrayList<String[]> inputData = prepareCardpaidTransactionList();
        storage.writeFile(inputData,cardNumber + CARD_PAID_TRANSACTION_LIST_FILE_NAME);
    }

    /**
     * Prepares goals records for goal list.
     *
     * @return arrayList that is ready to be written to the goal list file.
     */
    private ArrayList<String[]> prepareGoalsData() {
        ArrayList<String[]> exportArrayList = new ArrayList<>();
        exportArrayList.add(new String[]{
            "goalName", "amount", "date", "savingsAccountName", "doneStatus", "achieveStatus"});
        exportArrayList.add(new String[]{
            "BTO at Punggol",
            "25000.00",
            "10/11/2019",
            "",
            "false",
            "false"});
        exportArrayList.add(new String[]{
            "Europe Holiday",
            "20000.00",
            "08/01/2020",
            "",
            "false",
            "false"});
        exportArrayList.add(new String[]{
            "Car COE",
            "35000.00",
            "11/11/2020",
            "MayBank Savings Account",
            "false",
            "false"});
        exportArrayList.add(new String[]{
            "BFF Birthday",
            "200",
            "19/11/2019",
            "",
            "false",
            "false"});
        return exportArrayList;
    }

    /**
     * Creates the goal list file.
     *
     * @throws IOException If there are errors creating the goal list file.
     */
    private void createGoalListFile() throws IOException {
        ArrayList<String[]> inputData = prepareGoalsData();
        storage.writeFile(inputData,PROFILE_GOAL_LIST_FILE_NAME);
    }

    /**
     * Prepares achievement records for achievement list.
     *
     * @return arrayList that is ready to be written to the achievement list file.
     */
    private ArrayList<String[]> prepareAchievementData() {
        ArrayList<String[]> exportArrayList = new ArrayList<>();
        exportArrayList.add(new String[] {"achievementName", "amount", "category", "date"});
        return exportArrayList;
    }

    /**
     * Creates the achievement list file.
     *
     * @throws IOException If there are errors creating the achievement list file.
     */
    private void createAchievementListFile() throws IOException {
        ArrayList<String[]> inputData = prepareAchievementData();
        storage.writeFile(inputData,PROFILE_ACHIEVEMENT_LIST_FILE_NAME);
    }
}
