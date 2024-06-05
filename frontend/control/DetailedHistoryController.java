package control;

import java.io.IOException;
import model.Client;
import view.full_size_view.DetailedHistoryView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.TableModel;

/**
 *
 * @author admin
 */
public class DetailedHistoryController {
    private final DetailedHistoryView view;
    private Client currentClient;
    private List<Integer> monthlyAmount;
    private List<String> monthlyDates;
    private double standardDeviation;
    private double mean;
    private final String sessionKey;
    
    
    public DetailedHistoryController(String sessionKey) throws ParseException {
        this.sessionKey = sessionKey;
        view = new DetailedHistoryView(this, sessionKey);
        view.updateView();
        verifySession();
    }
    
    public void setViewData(Client client) throws ParseException, IOException {
        verifySession();
        this.currentClient = client;
        currentClient.updateForDetailedView();
        monthlyDates = client.getMonthlyDates();
        monthlyAmount = client.getMonthlyAmount();
        mean = client.getMean();
        standardDeviation = client.getStandardDeviation();
        
        view.meanLabel.setText("$" + MainController.formatAmount((int) Math.ceil(mean/100) * 100));
        view.stdDevUpLimLabel.setText("$" + MainController.formatAmount((int) Math.ceil((mean + standardDeviation)/100) * 100));
        view.historyPanel.setOpaque(false);
        view.nameLabel.setText(client.getName());
        view.nickLabel.setText(client.getNick());
        if(!client.getPhone().equals("")) {
            view.cpNumberLabel.setText(client.getPhone());
        }
        else {
            view.cpNumberLabel.setText("<No registra>");
        }
        view.notPaidBalanceLabel.setText("$" + MainController.formatAmount(client.getTotalNotPaidBalance()));
        view.setMonthlyHistoryTableModel();
        view.setTotalHistoryTable();
        try {
            view.displayChart();
        } catch (ParseException ex) {
            Logger.getLogger(DetailedHistoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public DetailedHistoryView getView() {
        return view;
    }

    public List<Integer> setChartableMonthlyAmounts() {
        List<Integer> chartableMonthlyAmounts;
        chartableMonthlyAmounts =  new ArrayList<>();
        for(int amount: monthlyAmount) {
            chartableMonthlyAmounts.add(amount);
        }
        return chartableMonthlyAmounts;
    }

    public List<String> setChartableMonthlyDates() {
        List<String> chartableMonthlyDates;
        chartableMonthlyDates = new ArrayList<>();
        for(String date: monthlyDates) {
            chartableMonthlyDates.add(date);
        }
        return chartableMonthlyDates;
    }

    public List<String> setControlDates() throws ParseException {
        SimpleDateFormat formater;
        formater = new SimpleDateFormat("yyyy-MM");
        
        Date thisMonth;
        thisMonth = 
                formater.
                        parse(formater.format(new Date()));
        
        List<String> controlDates;
        controlDates = new ArrayList<>();
        
        for(int i = 0; i < 6; i++) {
            controlDates.add(null);
        }
        for(int i = controlDates.size() - 1; i >= 0 ; i--) {
            controlDates.set(
                    i,
                    formater.format(
                            formater.parse(
                                    (formater.format(thisMonth).substring(0, 5)) + (Integer.parseInt(formater.format(thisMonth).substring(5, 7)) - i))
                    )
            );
        }
        return controlDates;
    }

    public void SetChartableListsSize(List<Integer> chartableMonthlyAmounts, List<String> chartableMonthlyDates) {
        if(chartableMonthlyAmounts.size() < 6) {
            for(int i = 0; i < (6 - chartableMonthlyAmounts.size()); i++) {
                chartableMonthlyAmounts.add(0, null);
                chartableMonthlyDates.add(0, null);
            }
        }
        if(chartableMonthlyAmounts.size() > 6) {
            chartableMonthlyAmounts = chartableMonthlyAmounts.subList(
                    chartableMonthlyAmounts.size() - 6,
                    chartableMonthlyAmounts.size()
            );
            chartableMonthlyDates = chartableMonthlyDates.subList(
                    chartableMonthlyDates.size() - 6,
                    chartableMonthlyDates.size()
            );
        }
    }

    public List<Integer> setFinalAmounts(
                List<String> controlDates, 
                List<String> chartableMonthlyDates, 
                List<Integer> chartableMonthlyAmounts) {
        List<Integer> finalAmounts;
        finalAmounts = new ArrayList<>();
        for(int i = 0; i < 6; i++) {
            finalAmounts.add(null);
        }
        
        for(int i = controlDates.size() -1; i >= 0; i--) {
            for(int j = chartableMonthlyDates.size() -1; j >= 0; j--) {
                if(controlDates.get(i).equals(chartableMonthlyDates.get(j))) {
                    finalAmounts.set(i, chartableMonthlyAmounts.get(j));
                }
            }
        }
        return finalAmounts;
    }
    
    private void verifySession() {
        MainController.authenticate(sessionKey);
    }

    public double getStandardDeviation() {
        return standardDeviation;
    }

    public double getMean() {
        return mean;
    }

    public Client getCurrentClient() {
        return currentClient;
    }

    public List<Integer> getMonthlyAmount() {
        return monthlyAmount;
    }

    public List<String> getMonthlyDates() {
        return monthlyDates;
    }
}