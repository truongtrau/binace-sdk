
import com.binance.dex.api.client.BinanceDexApiClientFactory;
import com.binance.dex.api.client.BinanceDexApiRestClient;
import com.binance.dex.api.client.BinanceDexEnvironment;
import com.binance.dex.api.client.Wallet;
import com.binance.dex.api.client.domain.OrderSide;
import com.binance.dex.api.client.domain.OrderType;
import com.binance.dex.api.client.domain.TimeInForce;
import com.binance.dex.api.client.domain.TransactionMetadata;
import com.binance.dex.api.client.domain.broadcast.*;


import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Main {

    private static BinanceDexApiRestClient client =
            BinanceDexApiClientFactory.newInstance().newRestClient(BinanceDexEnvironment.TEST_NET.getBaseUrl());

    public static void main(String[] args){
        System.out.println("hello");
        Wallet wallet = new Wallet("af8c545fd5f3e7f53b37b3c6750dc585ef21e80ac3907b5636fa9af019b36db5", BinanceDexEnvironment.TEST_NET);

        System.out.println(wallet.getAddress());
        System.out.println(wallet.getAddress());

        try {
            testTransfer();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            testSeeword();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


    /*    try {
            testNewOrder();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }*/

    }


    public static void testSeeword() throws IOException, NoSuchAlgorithmException {
        String seed = "trumpet lyrics attract toddler replace correct horror merge arrest pass lecture usage";
        String[] items  = seed.split(" ");
        List<String> itemListSeed = Arrays.asList(items);

        Wallet wallet =  Wallet.createWalletFromMnemonicCode(itemListSeed,BinanceDexEnvironment.TEST_NET);
        System.out.println(wallet.getAddress());
        System.out.println(wallet.getPrivateKey());
        System.out.println(seed);

    }

    public static void testTransfer() throws IOException, NoSuchAlgorithmException {
        Wallet wallet = new Wallet("af8c545fd5f3e7f53b37b3c6750dc585ef21e80ac3907b5636fa9af019b36db5", BinanceDexEnvironment.TEST_NET);
        //wallet wallet = new    Wallet.createWalletFromMnemonicCode(, BinanceDexEnvironment.TEST_NET);
        String symbol = "BNB";
        Transfer transfer = new Transfer();
        transfer.setCoin(symbol);
        transfer.setFromAddress(wallet.getAddress());
        transfer.setToAddress("tbnb1v4veuu96hl4ev0vntua4m3anvv758a04ss7z0s");
        transfer.setAmount("1");
        TransactionOption options = new TransactionOption("test", 1, null);
        List<TransactionMetadata> resp = client.transfer(transfer, wallet, options, true);
        System.out.println(resp.get(0));
    }

    public static void testNewOrder() throws IOException, NoSuchAlgorithmException {
        Wallet wallet = new Wallet("af8c545fd5f3e7f53b37b3c6750dc585ef21e80ac3907b5636fa9af019b36db5", BinanceDexEnvironment.TEST_NET);
        String symbol = "ZCB-F00_BNB";
        NewOrder no = new NewOrder();
        no.setTimeInForce(TimeInForce.GTE);
        no.setOrderType(OrderType.LIMIT);
        no.setSide(OrderSide.SELL);
        no.setPrice("20.7");
        no.setQuantity("1");
        no.setSymbol(symbol);

        TransactionOption options = TransactionOption.DEFAULT_INSTANCE;
        List<TransactionMetadata> resp = client.newOrder(no, wallet, options, true);
        System.out.println(resp.get(0));
    }

}
