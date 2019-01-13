package com.gao;

import com.gao.entity.Cat;
import com.gao.entity.Trade;
import com.gao.entity.Transaction;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestStreamInAction {
    Trade mario;
    Trade alan;
    Trade brain;
    Trade raoul;
    List<Transaction> transactions;

    @Before
    public void setUp() throws Exception {
        mario = new Trade ("Mario", "Milan");
        alan = new Trade ("Alan", "Cambridge");
        brain = new Trade ("Brian", "Cambridge");
        brain = new Trade ("Raoul", "NewYork");
        transactions = Arrays.asList (new Transaction (brain, 2011, 300), new Transaction (raoul, 2012, 1000), new Transaction (raoul, 2011, 400), new Transaction (mario, 2011, 710), new Transaction (mario, 2012, 700), new Transaction (alan, 2012, 950));
    }

    @Test
    public void testGetValue() {
//       获取2011年度的交易金额
        Optional<Integer> reduce = transactions.stream ().filter (t -> t.getYear () == 2011).map (t -> t.getYear ()).reduce ((i, j) -> i + j);
        Integer integer = reduce.orElse (-1);
        System.out.println (integer);
//        查找2011年所有交易员且按交易金额从小到大排序
        List<Transaction> tra = transactions.stream ().filter (t -> t.getYear () == 2011).sorted (Comparator.comparing (Transaction::getValue)).collect (Collectors.toList ());
        System.out.println (tra);

    }

    @Test
    public void testOptional() {
        Transaction transaction = transactions.stream ().max (Comparator.comparing (Transaction::getValue)).get ();
        Optional<Transaction> catOptional = Optional.of (transaction);
        transaction = catOptional.filter (t -> t.getValue () > 1200).orElse (new Transaction ());
        System.out.println (transaction);

    }
}
