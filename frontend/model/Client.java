package model;

import control.MainController;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import model.IO.Reader;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import org.apache.commons.math3.stat.descriptive.moment.Mean;

/**
 * @description: This class is used to store client-related data for general
 * purpose inside the application.
 *
 * @author Jonathan Torres
 * @since 28/01/2021
 * @version 1.0
 */
public class Client {

    private final int id;
    private String name;
    private String nick;
    private String phone;
    private String commentary;
    private float interestRate;
    private float interestRateOnDefault;
    private final float daysToDefault;
    private List<Debt> debts;
    private List<Deposit> deposits;
    private double mean;
    private double standardDeviation;
    private List<Integer> monthlyAmount;
    private int defaultAmount;
    private List<String> monthlyDates;
    private boolean defaulter;
    private final String createdBy;
    private final int creatorId;
    private boolean detailedUpdated;
    private boolean disabled;

    public Client(int id,
            String name,
            String nick,
            String phone,
            String commentary,
            float interestRate,
            float interestRateOnDefault,
            int daysToDefault,
            String createdBy,
            int creatorId,
            boolean disabled
    ) throws ParseException {
        this.id = id;
        this.name = name;
        this.nick = nick;
        if (phone != null) {
            this.phone = phone;
        } else {
            this.phone = "";
        }
        this.commentary = commentary;
        this.interestRate = interestRate;
        this.interestRateOnDefault = interestRateOnDefault;
        this.daysToDefault = daysToDefault;
        this.createdBy = createdBy;
        this.creatorId = creatorId;
        this.detailedUpdated = false;
        this.disabled = disabled;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public int getCreatorId() {
        return creatorId;
    }

    /**
     * Total not paid balance for current client.
     *
     * @return float totalNotPaidBalance
     */
    private float getNotPaidBalance() throws IOException, ParseException {
        setDebts();
        float totalNotPaidBalance;
        totalNotPaidBalance = 0f;
        for (Debt debt : debts) {
            if (!debt.isPaid()) {
                if (!debt.isVoid()) {
                    totalNotPaidBalance += debt.getTotalDebt();
                }
            }
        }
        return totalNotPaidBalance;
    }

    public String getName() {
        return name;
    }

    /**
     * Sets the current client commentary
     *
     * @param commentary
     */
    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNick() {
        return nick;
    }

    public String getPhone() {
        return phone;
    }

    public List<Debt> getDebts() throws IOException, ParseException {
        return debts;
    }

    public List<Deposit> getDeposits() throws IOException, ParseException {
        setDeposits();
        return deposits;
    }

    public int getId() {
        return id;
    }

    public float getTotalNotPaidBalance() throws IOException, ParseException {
        return getNotPaidBalance();
    }

    public String getCommentary() {
        return commentary;
    }

    public void sortDebts() throws ParseException, IOException {
        setDebts();

//        ArrayList<Date> dates;
//        dates = new ArrayList<>();
//
//        for (Debt debt : debts) {
//            dates.add(MainController.parseStringToDate(debt.getCreationDate()));
//        }
//
//        Collections.sort(dates);
//
//        for (int i = 0; i < dates.size(); i++) {
//            for (int j = i; j < debts.size(); j++) {
//                if (dates.get(i).equals(MainController.parseStringToDate(debts.get(j).getCreationDate()))) {
//                    Collections.swap(debts, i, j);
//                }
//            }
//        }
    }

    private void setMean() {
        double[] values;
        values = new double[0];
        if (monthlyAmount.size() > 0) {
            values = new double[monthlyAmount.subList(0, monthlyAmount.size() - 1).size()];
            for (int i = 0; i < monthlyAmount.subList(0, monthlyAmount.size() - 1).size(); i++) {
                values[i]
                        = monthlyAmount.subList(0, monthlyAmount.size() - 1).get(i);
            }
        }
        mean = new Mean().evaluate(values);
    }

    private void setStandardDeviation() {
        double[] values;
        values = new double[0];
        if (monthlyAmount.size() > 0) {
            values = new double[monthlyAmount.subList(0, monthlyAmount.size() - 1).size()];

            for (int i = 0; i < monthlyAmount.subList(0, monthlyAmount.size() - 1).size(); i++) {
                values[i] = monthlyAmount.subList(0, monthlyAmount.size() - 1).get(i);
            }
        }

        standardDeviation = new StandardDeviation().evaluate(values);
    }

    private void setMonthlyDates() throws ParseException, IOException {
        monthlyDates = new ArrayList<>();

        if (debts.size() > 0) {

            SimpleDateFormat formater;
            formater = new SimpleDateFormat("yyyy-MM");

            Calendar calendar;
            calendar = Calendar.getInstance();

            calendar.setTime(formater.parse(debts.get(0).getCreationDate().substring(0, 8)));
            
            String then = formater.format(calendar.getTime());
            String now = formater.format(new Date());
            
            while (!then.equals(now)) {
                monthlyDates.add(then);
                calendar.add(Calendar.MONTH, 1);
                then = formater.format(calendar.getTime());
            }
            monthlyDates.add(now);
        }
    }

    public boolean isDefaulter() {
        return defaulter;
    }

    public int getDefaultAmount() {
        return defaultAmount;
    }

    private void setDefaultAmount() throws ParseException, IOException {
        defaultAmount = 0;

        for (int i = 0; i < debts.size(); i++) {
            if (debts.get(i).isInDefault()) {
                defaultAmount += debts.get(i).getTotalDebt();
            }
        }

        defaulter = defaultAmount > 0;
    }

    private void setMonthlyAmount() throws ParseException {

        monthlyAmount = new ArrayList<>();

        for (int i = 0; i < monthlyDates.size(); i++) {
            monthlyAmount.add(0);
        }

        //Date generator for comparison
        SimpleDateFormat monthFormater;
        monthFormater = new SimpleDateFormat("yyyy-MM");

        //Grouping by date
        for (int i = 0; i < debts.size(); i++) {

            for (int j = 0; j < monthlyDates.size(); j++) {
                int index;
                index = monthFormater.parse(debts.get(i).getCreationDate().substring(0, 7)).
                        compareTo(monthFormater.parse(monthlyDates.get(j)));

                if (index == 0) {
                    monthlyAmount.set(j, monthlyAmount.get(j) + debts.get(i).getAmount());
                }
            }
        }
    }

    public double getMean() {
        return mean;
    }

    public double getStandardDeviation() {
        return standardDeviation;
    }

    public List<Integer> getMonthlyAmount() {
        return monthlyAmount;
    }

    public List<String> getMonthlyDates() {
        return monthlyDates;
    }

    public void updateDefaultAmount() throws ParseException, IOException {
        setDefaultAmount();
    }

    public void updateForDetailedView() throws ParseException, IOException {
        if (!detailedUpdated) {
            setMonthlyDates();
            setMonthlyAmount();
            setMean();
            setStandardDeviation();
            detailedUpdated = true;
        }
    }

    public float getInterestRate() {
        return interestRate;
    }

    public float getInterestRateOnDefault() {
        return interestRateOnDefault;
    }

    public void setInterestRate(float newInterestRate) {
        this.interestRate = newInterestRate;
    }

    public void setInterestRateOnDefault(float newInterestRateOnDefault) {
        this.interestRateOnDefault = newInterestRateOnDefault;
    }

    public float getDaysToDefault() {
        return daysToDefault;
    }

    public void setDebts() throws IOException, ParseException {
        if (this.debts == null) {
            updateDebts();
        }
    }

    public void updateDebts() throws IOException, ParseException {
        this.debts = Reader.getClientDebts(this);
    }

    public void setDebts(List<Debt> debts) {
        this.debts = debts;
    }

    public void setDeposits() throws IOException, ParseException {
        if (this.deposits == null) {
            updateDeposits();
        }
    }

    public void updateDeposits() throws IOException, ParseException {
        this.deposits = Reader.getClientDeposits(this);
    }

    public void setDeposits(List<Deposit> deposits) {
        this.deposits = deposits;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void addDebt(Debt newDebt) throws ParseException {
        Date today;
        today = MainController.parseStringToDate(MainController.getTodayDateToString());

        Date newDebtDate;
        newDebtDate = MainController.parseStringToDate(newDebt.getCreationDate());

        long daysOfDifference;
        daysOfDifference = MainController.daysOfDifference(newDebtDate, today);

        if (daysOfDifference == 0l) {
            debts.add(newDebt);
        } else {
            
            boolean inserted = false;
            //verify for existant dates
            if (!inserted) {
                for (int i = debts.size() - 1; i >= 0; i--) {
                    Date date = MainController.parseStringToDate(debts.get(i).getCreationDate());
                    if (MainController.daysOfDifference(newDebtDate, date) == 0l) {
                        debts.add(i + 1, newDebt);
                        inserted = true;
                        break;
                    }
                }
            }
            //verify for future from last inserted
            if (!inserted) {
                Date firstDebtDate = MainController.parseStringToDate(debts.get(debts.size() - 1).getCreationDate());
                if(MainController.daysOfDifference(newDebtDate, firstDebtDate) < 0) {
                    debts.add(debts.size(), newDebt);
                    inserted = true;
                }
            }
            //verify for past non-existant date
            if(!inserted) {
                Date previousDate;
                Date currentDate = MainController.parseStringToDate(debts.get(debts.size() - 1).getCreationDate());
                for (int i = debts.size() - 2; i >= 0; i--) {
                    previousDate = currentDate;
                    currentDate = MainController.parseStringToDate(debts.get(i).getCreationDate());
                    
                    boolean greaterThanPrevious;
                    greaterThanPrevious = MainController.daysOfDifference(newDebtDate, previousDate) > 0;
                    
                    boolean lowerThanCurrent;
                    lowerThanCurrent = MainController.daysOfDifference(newDebtDate, currentDate) < 0;
                    
                    if (greaterThanPrevious && lowerThanCurrent) {
                        debts.add(i + 1, newDebt);
                        inserted = true;
                        break;
                    }
                }
            }
            if(!inserted) {
                debts.add(0, newDebt);
            }
        }
    }
}
