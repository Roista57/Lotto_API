package com.lotto.service;

import java.util.ArrayList;
import java.util.Collections;

import org.springframework.stereotype.Service;

@Service
public class LottoService {
	
	public ArrayList<Integer> randomLottoNumber(){
		ArrayList<Integer> lottoNumber = new ArrayList<>();
		for (int i = 1; i <= 45; i++) {
			lottoNumber.add(i);
		}
		Collections.shuffle(lottoNumber);

		ArrayList<Integer> ans = new ArrayList<>();
		for (int i = 0; i < 6; i++) {
			ans.add(lottoNumber.get(i));
		}
		Collections.sort(ans);
		return ans;
	}
	
	
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
