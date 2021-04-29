package com.example.theshovonsaha.f_bank.model;


public class Bank {
    private Client[] clients;
    private final int MAX_NUM_CLIENTS = 6;
    private int noc;
    private boolean error;
    private String errorMsg;

    public Bank() {
        clients = new Client[MAX_NUM_CLIENTS];
    }

    public void addClient(String name, double balance) {
        int index = indexOfClients(name);
        if(noc >= MAX_NUM_CLIENTS) {
            setError("Error: Maximum Number of Accounts Reached");
        }
        else if(index >= 0) {
            setError("Error: Client " + name + " already exists");
        }
        else if(balance <= 0) {
            setError("Error: Non-Positive Initial Balance");
        }
        else {
            resetError();
            Client c = new Client(name, balance);
            clients[noc] = c;
            noc ++;
        }
    }

    public void deposit(String name, double amount) {
        int index = indexOfClients(name);
        if(index < 0) {
            setError("Error: To-Account " + name + " does not exist");
        }
        else if(amount <= 0) {
            setError("Error: Non-Positive Amount");
        }
        else {
            resetError();
            clients[index].deposit(amount);
        }
    }

    public void withdraw(String name, double amount) {
        int index = indexOfClients(name);
        if(index < 0) {
            setError("Error: From-Account " + name + " does not exist");
        }
        else if(amount <= 0) {
            setError("Error: Non-Positive Amount");
        }
        else if(clients[index].getBalance() < amount) {
            setError("Error: Amount too large to withdraw");
        }
        else {
            resetError();
            clients[index].withdraw(amount);
        }
    }

    public void transfer(String fromName, String toName, double amount) {
        int indexFrom = indexOfClients(fromName);
        int indexTo = indexOfClients(toName);
        if(indexFrom < 0) {
            setError("Error: From-Account " + fromName + " does not exist");
        }
        else if(indexTo < 0) {
            setError("Error: To-Account " + toName + " does not exist");
        }
        else if(amount <= 0) {
            setError("Error: Non-Positive Amount");
        }
        else if(clients[indexFrom].getBalance() < amount) {
            setError("Error: Amount too large to transfer");
        }
        else {
            resetError();
            clients[indexFrom].withdraw(amount);
            clients[indexTo].deposit(amount);
        }
    }

    public String[] getStatement(String name) {
        String[] s = null;
        int index = indexOfClients(name);
        if(index < 0) {
            setError("Error: From-Account " + name + " does not exist");
        }
        else {
            s = clients[index].getStatement();
        }
        return s;
    }

    public String getStatus() {
        String s;
        if(error) {
            s = errorMsg;
        }
        else {
            s = "Accounts: {";
            for(int i = 0; i < noc; i ++) {
                Client c = clients[i];
                s += c.getStatus();
                if(i < this.noc - 1) {
                    s += ", ";
                }
            }
            s += "}";
        }
        return s;
    }

    /*
     * Helper Methods
     */
    private void setError(String msg) {
        error = true;
        errorMsg = msg;
    }

    private void resetError() {
        error = false;
        errorMsg = "";
    }

    private int indexOfClients(String name) {
        int index = -1;
        for(int i = 0; i < noc; i ++) {
            if(clients[i].getName().equals(name)) {
                index = i;
            }
        }
        return index;
    }
}
