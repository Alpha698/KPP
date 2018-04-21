/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package var2;

/**
 *
 * @author Alpha
 */
import java.util.*;
import java.io.*;

public class CardOperation implements Serializable {
public CardOperation(String card,double amount,Date operationDate){ this.card = card;
this.amount = amount;
this.operationDate = operationDate;
}
public String card;
public double amount;
public Date operationDate;
}
