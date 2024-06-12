package com.lotto.controller;

import java.util.ArrayList;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lotto.batch.LottoModelBatch;
import com.lotto.service.LottoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin("*")
@Tag(name = "", description = "")
@RequestMapping("/lotto")
public class LottoController {
	@Autowired
	private LottoService lservice;
	
	private final LottoModelBatch scriptRunner = new LottoModelBatch();
	
	// 랜덤한 6개의 숫자를 보내줌
	@GetMapping("random")
	@Operation(summary = "", description = "")
	public ArrayList<Integer> getRandom() {
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
	
	@GetMapping("/lstm/random")
    public ArrayList<Integer> predictLottoNumbers() {
		String ans = scriptRunner.runPythonScript("F:\\ToyProject\\Lotto API\\LottoCrawling\\load_model.py");
		if(ans.equals("fail")) {
			return null;
		}
        return lservice.convertStringToArrayList(ans);
    }

}
