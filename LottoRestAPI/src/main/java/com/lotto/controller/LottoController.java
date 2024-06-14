package com.lotto.controller;
import java.util.ArrayList;

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
@Tag(name = "로또 번호 추첨", description = "로또 번호를 추첨하는 컨트롤러")
@RequestMapping("/lotto")
public class LottoController {
	@Autowired
	private LottoService lservice;
	@Autowired
	private LottoModelBatch scriptRunner;
	
	// 랜덤한 6개의 숫자를 보내줌
	@GetMapping("random")
	@Operation(summary = "", description = "")
	public ArrayList<Integer> getRandomNumber() {
		return lservice.randomLottoNumber();
	}
	
	// 파이썬을 실행한 결과를 반환해줌
	@GetMapping("/lstm/random")
    public ArrayList<Integer> getScriptLottoNumbers() {
		String ans = scriptRunner.runPythonScriptLoadModel();
		if(ans.equals("fail")) {
			return null;
		}
        return lservice.convertStringToArrayList(ans);
    }
}
