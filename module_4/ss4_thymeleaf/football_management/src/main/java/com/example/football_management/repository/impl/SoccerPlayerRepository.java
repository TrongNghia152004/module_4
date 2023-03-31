package com.example.football_management.repository.impl;

import com.example.football_management.model.SoccerPlayer;
import com.example.football_management.repository.ISoccerPlayerRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SoccerPlayerRepository implements ISoccerPlayerRepository {
    private static List<SoccerPlayer> soccerPlayerList = new ArrayList<>();

    static {
        soccerPlayerList.add(new SoccerPlayer(1, "01", "Trần Trọng Nghĩa", "01/05/2004", "Chuyên nghiệp", "Tiền vệ", "https://i.vietgiaitri.com/2018/9/28/sao-nam-phim-khieu-dam-84-tuoi-tiet-lo-bi-quyet-ben-bi-trong-chu-fab71f.jpeg"));
        soccerPlayerList.add(new SoccerPlayer(2, "02", "Phan Văn Đồng", "01/06/2004", "Chuyên nghiệp", "Hậu vệ", "https://i.vietgiaitri.com/2018/9/28/sao-nam-phim-khieu-dam-84-tuoi-tiet-lo-bi-quyet-ben-bi-trong-chu-fab71f.jpeg"));
        soccerPlayerList.add(new SoccerPlayer(3, "03", "Lê Trần Anh Huy", "01/07/2004", "Chuyên nghiệp", "Tiền đạo", "https://i.vietgiaitri.com/2018/9/28/sao-nam-phim-khieu-dam-84-tuoi-tiet-lo-bi-quyet-ben-bi-trong-chu-fab71f.jpeg"));
    }

    @Override
    public List<SoccerPlayer> findAll() {
        return soccerPlayerList;
    }

    @Override
    public SoccerPlayer findById(int id) {
        for (int i = 0; i < soccerPlayerList.size(); i++) {
            if (soccerPlayerList.get(i).getId() == id) {
                return soccerPlayerList.get(i);
            }
        }
        return null;
    }

    @Override
    public void deleteSoccerPlayer(int id) {
        soccerPlayerList.remove(findById(id));
    }

    @Override
    public void create(SoccerPlayer soccerPlayer) {
        soccerPlayer.setId(soccerPlayerList.size() + 1);
        soccerPlayerList.add(soccerPlayer);
    }

    @Override
    public void update(SoccerPlayer soccerPlayer) {
        for (SoccerPlayer upSoccerPlayer : soccerPlayerList) {
            if (upSoccerPlayer.getId() == soccerPlayer.getId()) {
                upSoccerPlayer.setCode(soccerPlayer.getCode());
                upSoccerPlayer.setName(soccerPlayer.getName());
                upSoccerPlayer.setDateOfBirth(soccerPlayer.getDateOfBirth());
                upSoccerPlayer.setExp(soccerPlayer.getExp());
                upSoccerPlayer.setLocation(soccerPlayer.getLocation());
                upSoccerPlayer.setImage(soccerPlayer.getImage());
            }
        }
    }
}
