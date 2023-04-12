function movePage(page) {
    getSoccerPlayerList(page);
}

function renderPage(soccerPlayerList) {
    let pageable = "";
    if (
        soccerPlayerList.number <= soccerPlayerList.totalPages - 1 &&
        soccerPlayerList.number > 0
    ) {
        pageable += `
    <button class="page-item btn btn-dark btn-sm" 
    onclick="movePage(${soccerPlayerList.number - 1})">
    Previous
    </button>
    `;
    }
    for (let i = 1; i <= soccerPlayerList.totalPages; i++) {
        let pageItem = $(`<button class="page-item number btn btn-dark btn-sm"
                      onclick="movePage(${i - 1})">
                      ${i}
                      </button>`);
        if (i === soccerPlayerList.number + 1) {
            pageItem.addClass("active1");
        } else {
            pageItem.removeClass("active1");
        }
        pageable += pageItem.prop("outerHTML");
    }

    if (soccerPlayerList.number >= 0 && soccerPlayerList.number < soccerPlayerList.totalPages - 1) {
        pageable += `
    <button class="page-item btn btn-dark btn-sm" 
    onclick="movePage(${soccerPlayerList.number + 1})">
    Next
    </button>
    `;
    }
    $("#pagination").html(pageable);
}

function getSoccerPlayerIdAndName(id, name) {
    document.getElementById("deleteId").value = id;
    document.getElementById("deleteName").innerText = name;
}

// list
function renderSoccerPlayerList(soccerPlayerList) {
    debugger;
    let elements = "";
    let stt = (soccerPlayerList.number) * soccerPlayerList.size + 1;
    for (let soccerPlayer of soccerPlayerList.content) {
        elements += `<tr>
          <td>${stt++}</td>
          <td>${soccerPlayer.code}</td>
          <td>${soccerPlayer.name}</td>
          <td>${soccerPlayer.dateOfBirth}</td>
          <td>${soccerPlayer.exp}</td>
          <td>${soccerPlayer.location}</td>
          <td>
          <img style="width: 69px; height: 69px" src="${soccerPlayer.image}" alt="">
          </td>
          <td>${soccerPlayer.status}</td>
          <td>${soccerPlayer.team.name}</td>
          <td>
          </tr>`;
    }
    $("#list-soccer-player").html(elements);
}

function getSoccerPlayerList(page) {
    let search = $("#search").val();
    $.ajax({
        type: "get",
        url: `http://localhost:8080/api/soccer-player?name=${search}&page=${
            page ? page : "0"
        }`,
        headers: {
            "Content-Type": "application/json",
        },
        success: function (data) {
            debugger;
            console.log(data)
            if (data.totalElements === 0) {
                document.getElementById("listNull").innerHTML = "Danh sách rỗng";
            } else {
                document.getElementById("listNull").innerHTML = "";
            }
            renderSoccerPlayerList(data);
            renderPage(data);
        },
        error: function (error) {
            console.log(error);
        },
    });
}

$(document).ready(function () {
    getSoccerPlayerList();
});

// add
$("#add-soccer-player").submit(function (event) {
    debugger;
    event.preventDefault();
    let code = $("#code").val();
    let name = $("#name").val();
    let dateOfBirth = $("#date").val();
    let exp = $("#exp").val();
    let location = $("#location").val();
    let image = $("#image").val();
    let team = $("#team").val();
    addSoccerPlayer(code, name, dateOfBirth, exp, location, image, team);
});

function addSoccerPlayer(code, name, dateOfBirth, exp, location, image, team) {
    $.ajax({
        headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
        },
        url: `http://localhost:8080/api/soccer-player`,
        type: "post",
        data: JSON.stringify({
            code: code,
            name: name,
            dateOfBirth: dateOfBirth,
            exp: exp,
            location: location,
            image: image,
            team: {id: team},
        }),
        success: function (data) {
            alert("Thêm cầu thủ thành công!");
            $("#addSoccerPlayer").hide();
            $("body").removeClass("modal-open");
            $(".modal-backdrop").remove();
            getSoccerPlayerList();
        },
        error: function (data) {
            debugger;
            console.log(Object.keys(data.responseJSON))
            for (let key of Object.keys(data.responseJSON)) {
                const cusKey = `${key[0].toUpperCase()}${key.substring(1)}`;
                if (document.getElementById(`add${cusKey}Valid`)) {
                    document.getElementById(`add${cusKey}Valid`).innerText = data.responseJSON[key] ?? '';
                }
            }
        },
    })
}

function getSelectTeamList() {
    $.ajax({
        type: "GET",
        url: `http://localhost:8080/team?name=${""}`,
        headers: {
            "Content-Type": "application/json",
        },
        success: function (data) {
            showTeamSelectOptionCreate(data)
        },
        error: function (error) {
            console.log(error);
        },
    });
}

function showTeamSelectOptionCreate(teams) {
    let element = "";
    element += `
  <select class="form-control" id="teamDTO" name="teamDTO">`;

    for (let team of teams) {
        element += `<option value="${teams.id}">`;
        element += teams.name;
        `</option>`;
    }

    `</select>`;
    $("#teamDTO").html(element);
}

$(document).ready(function () {
    getSelectTeamList();
})