package com.lotto.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public class LottoService {
	public ArrayList<Integer> convertStringToArrayList(String str) {
        // 대괄호 제거
        str = str.substring(1, str.length() - 2);
        // 콤마를 기준으로 분리
        String[] numbers = str.split(", ");
        // String 배열을 Integer ArrayList로 변환
        ArrayList<Integer> result = new ArrayList<>();
        for (String number : numbers) {
            result.add(Integer.parseInt(number));
        }
        return result;
    }
}
