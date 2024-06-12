package com.lotto.batch;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class LottoModelBatch {
    private static final String VENV_PYTHON_PATH = "F:\\ToyProject\\Lotto API\\LottoCrawling\\.venv\\Scripts\\python.exe";

    @Scheduled(cron = "0 0 8 * * MON")  // 매주 월요일 오전 8시에 실행
    public void runCreateModelScript() {
        runPythonScript("F:\\ToyProject\\Lotto API\\LottoCrawling\\create_model.py");
    }

    public String runPythonScript(String scriptPath) {
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
            int exitCode = process.waitFor();
//            System.out.println("Execution finished with exit code: " + exitCode);
            return output.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }
}
