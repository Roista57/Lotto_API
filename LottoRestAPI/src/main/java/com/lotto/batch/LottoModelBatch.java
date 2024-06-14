package com.lotto.batch;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component // 스케쥴러를 스프링 빈에 등록
public class LottoModelBatch {
	private static final String VENV_PYTHON_PATH = "F:\\ToyProject\\Lotto API\\LottoCrawling\\.venv\\Scripts\\python.exe";

	@Scheduled(cron = "0 0 8 * * MON") // 매주 월요일 오전 8시에 실행 (cron: 특정 시간에 프로그램을 실행, 정규 표현식으로 나타냄)
	public void runCreateModelScript() {
		runPythonScriptCreateModel();
	}

	public String runPythonScriptCreateModel() {
		String scriptPath = "F:\\ToyProject\\Lotto API\\LottoCrawling\\create_model.py";
		try {
			ProcessBuilder processBuilder = new ProcessBuilder(VENV_PYTHON_PATH, scriptPath);
			processBuilder.redirectErrorStream(true);
			Process process = processBuilder.start();
			return "success";
		} catch (Exception e) {
//            e.printStackTrace();
			return "fail";
		}
	}

	public String runPythonScriptLoadModel() {
		String scriptPath = "F:\\ToyProject\\Lotto API\\LottoCrawling\\load_model.py";
		try {
			ProcessBuilder processBuilder = new ProcessBuilder(VENV_PYTHON_PATH, scriptPath);
			processBuilder.redirectErrorStream(true);
			Process process = processBuilder.start();

			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			StringBuilder output = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				output.append(line).append("\n");
			}
			return output.toString();
		} catch (Exception e) {
//            e.printStackTrace();
			return "fail";
		}
	}
}
