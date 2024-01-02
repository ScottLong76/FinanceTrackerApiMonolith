package com.longware.financetracker.service;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.xmlbeans.impl.xb.xsdschema.Attribute.Use;
import org.springframework.stereotype.Service;

import com.longware.financetracker.entities.Bank;
import com.longware.financetracker.entities.BankTransaction;
import com.longware.financetracker.entities.Deposit;
import com.longware.financetracker.entities.UserAccount;
import com.longware.financetracker.entities.Vendor;
import com.longware.financetracker.repository.BankTransactionRepository;
import com.webcohesion.ofx4j.domain.data.MessageSetType;
import com.webcohesion.ofx4j.domain.data.ResponseEnvelope;
import com.webcohesion.ofx4j.domain.data.ResponseMessage;
import com.webcohesion.ofx4j.domain.data.ResponseMessageSet;
import com.webcohesion.ofx4j.domain.data.banking.BankStatementResponseTransaction;
import com.webcohesion.ofx4j.domain.data.common.Payee;
import com.webcohesion.ofx4j.domain.data.common.Transaction;
import com.webcohesion.ofx4j.domain.data.common.TransactionList;
import com.webcohesion.ofx4j.domain.data.common.TransactionType;
import com.webcohesion.ofx4j.domain.data.creditcard.CreditCardStatementResponseTransaction;
import com.webcohesion.ofx4j.io.AggregateUnmarshaller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;

@Service
@RequiredArgsConstructor
@Getter
@Setter
@Log
public class BankTransactionService {

    private final BankTransactionRepository bankTransactionRepository;

    public void deleteById(Long id) {
        bankTransactionRepository.deleteById(id);
    }

    public void delete(BankTransaction bankTransaction) {
        bankTransactionRepository.delete(bankTransaction);
    }

    public void saveAll(Iterable<BankTransaction> bankTransactions) {
        bankTransactionRepository.saveAll(bankTransactions);
    }

    public BankTransaction save(BankTransaction bankTransaction) {
        return bankTransactionRepository.save(bankTransaction);
    }

    public Optional<BankTransaction> findById(Long id) {
        return bankTransactionRepository.findById(id);
    }

    public Iterable<BankTransaction> findAll() {
        return bankTransactionRepository.findAll();
    }

    public Iterable<BankTransaction> findAllById(Iterable<Long> ids) {
        return bankTransactionRepository.findAllById(ids);
    }

    public Iterable<BankTransaction> findByUserAccount(UserAccount userAccount) {
        return bankTransactionRepository.findByUserAccount(userAccount);
    }

    public long count() {
        return bankTransactionRepository.count();
    }

    public boolean entityExists(BankTransaction bankTransaction) {
        return bankTransactionRepository.entityExists(bankTransaction);
    }

    public Optional<BankTransaction> getEntity(BankTransaction bankTransaction) {
        return bankTransactionRepository.getEntity(bankTransaction);
    }

    public List<BankTransaction> processOFXFile(File file, Bank selectedUploadBank) {
        List<BankTransaction> parsedTransactions = new ArrayList<BankTransaction>();
        try {

            AggregateUnmarshaller<ResponseEnvelope> unmarshaller = new AggregateUnmarshaller<ResponseEnvelope>(
                    ResponseEnvelope.class);
            ResponseEnvelope envelope = unmarshaller.unmarshal(new FileInputStream(file));

            MessageSetType messageSetType = MessageSetType.banking;

            ResponseMessageSet messageSet = (ResponseMessageSet) envelope
                    .getMessageSet(messageSetType);

            if (messageSet == null) {
                messageSetType = MessageSetType.creditcard;
                messageSet = (ResponseMessageSet) envelope
                        .getMessageSet(MessageSetType.creditcard);
            }

            List<ResponseMessage> responses = messageSet.getResponseMessages();

            for (ResponseMessage response : responses) {
                TransactionList transactionList = null;
                if (response instanceof BankStatementResponseTransaction) {
                    transactionList = ((BankStatementResponseTransaction) response).getMessage().getTransactionList();
                } else if (response instanceof CreditCardStatementResponseTransaction) {
                    transactionList = ((CreditCardStatementResponseTransaction) response).getMessage()
                            .getTransactionList();
                } else {
                    log.warning("Unknown response type: " + response.getClass().getName());
                    return parsedTransactions;
                }
                List<Transaction> transactions = transactionList.getTransactions();
                for (Transaction ofxTransaction : transactions) {
                    BankTransaction transaction = new BankTransaction();
                    String description = "";
                    if (ofxTransaction.getMemo() != null) {
                        description += ofxTransaction.getMemo() + " ";
                    }
                    transaction.setDescription(description);

                    LocalDate transactionDate = null;
                    if (ofxTransaction.getDateInitiated() != null) {
                        transactionDate = ofxTransaction.getDateInitiated().toInstant().atZone(ZoneId.systemDefault())
                                .toLocalDate();
                    } else if (ofxTransaction.getDateAvailable() != null) {
                        transactionDate = ofxTransaction.getDateAvailable().toInstant().atZone(ZoneId.systemDefault())
                                .toLocalDate();
                    } else if (ofxTransaction.getDatePosted() != null) {
                        transactionDate = ofxTransaction.getDatePosted().toInstant().atZone(ZoneId.systemDefault())
                                .toLocalDate();
                    }
                    transaction.setTransactionDate(transactionDate);
                    Payee payee = ofxTransaction.getPayee();
                    if (payee != null && payee.getName() != null) {
                        String payeeDetails = "Name: " + payee.getName();
                        String payeeAddress = "";
                        if (payee.getAddress1() != null)
                            payeeAddress += (payee.getAddress1() + " ");
                        if (payee.getAddress2() != null)
                            payeeAddress += (payee.getAddress2() + " ");
                        if (payee.getAddress3() != null)
                            payeeAddress += (payee.getAddress3() + " ");
                        if (payee.getCity() != null)
                            payeeAddress += (payee.getCity() + " ");
                        if (payee.getState() != null)
                            payeeAddress += (payee.getState() + " ");
                        if (payee.getZip() != null)
                            payeeAddress += (payee.getZip() + " ");
                        if (payee.getCountry() != null)
                            payeeAddress += (payee.getCountry() + " ");
                        if (payee.getPhone() != null)
                            payeeAddress += (payee.getPhone() + " ");
                        if (payeeAddress.length() > 0) {
                            payeeAddress = "Address: " + payeeAddress;
                            payeeDetails += (" " + payeeAddress);
                        }
                        transaction.setPayee(payeeDetails);
                    }
                    TransactionType transactionType = ofxTransaction.getTransactionType();
                    if (transactionType != null) {
                        transaction.setTransactionType(transactionType.toString());
                    }
                    parsedTransactions.add(transaction);
                }
                if (parsedTransactions.size() > 0) {
                    bankTransactionRepository.saveAll(parsedTransactions);
                }
            }

        } catch (Exception ex) {
            log.severe("Error occurred while parsing OFX file: " + ex.getMessage());
            log.severe("Exception details: ");
            log.severe(Arrays.toString(ex.getStackTrace()));
        }
        return parsedTransactions;
    }

    public void updateBankTransactionsByEntityAndRegex(Object entityObject, List<String> regexList,
            UserAccount userAccount) {
        if (!(entityObject instanceof Vendor || entityObject instanceof Deposit)) {
            log.warning("Invalid entity type passed to updateBankTransactionsByEntityAndRegex method");
            return;
        }
        int updatedTransactions = 0;
        Iterable<BankTransaction> bankTransactions = bankTransactionRepository.findByUserAccount(userAccount);
        for (BankTransaction bankTransaction : bankTransactions) {
            for (String regex : regexList) {
                if (bankTransaction.getDescription().matches(regex)) {
                    updatedTransactions++;
                    if (entityObject instanceof Vendor) {
                        bankTransaction.setVendor((Vendor) entityObject);
                    } else if (entityObject instanceof Deposit) {
                        bankTransaction.setDeposit((Deposit) entityObject);
                    }
                    break;
                }
            }
        }

        log.info("Updated " + updatedTransactions + " bank transactions");
        // Save the updated Bank Transactions to the database
        bankTransactionRepository.saveAll(bankTransactions);
    }

}