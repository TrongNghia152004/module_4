package com.example.soccer_player_management.aop;

import com.example.soccer_player_management.model.SoccerPlayer;
import com.example.soccer_player_management.service.ISoccerPlayerService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;

@Aspect
@Component
public class SoccerPlayerAspect {
    @Autowired
    private ISoccerPlayerService soccerPlayerService;
    @After("execution(* com.example.soccer_player_management.controller.SoccerPlayerController.*(..))")
    public void logAfterSoccerPlayer(JoinPoint joinPoint) {
        List<SoccerPlayer> list = soccerPlayerService.footballTeam();
        int number = list.size();
        String methodName = joinPoint.getSignature().getName();
        try {
            File file = new File("D:\\module_4\\module_4\\ss6_sping_boot\\soccer_player_management\\src\\main\\java\\com\\example\\soccer_player_management\\file\\football.txt");
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));
            String line;
            if ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            File file = new File("D:\\module_4\\module_4\\ss6_sping_boot\\soccer_player_management\\src\\main\\java\\com\\example\\soccer_player_management\\file\\football.txt");
            FileOutputStream outputStream = new FileOutputStream(file, true);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
            writer.write(methodName + " Số lượng cầu thủ đăng ký đá : " + number + "\n");
            writer.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
