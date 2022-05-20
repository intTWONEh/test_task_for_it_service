function getTask() {
    $.ajax({
        type : "GET",
        url : "tasks",
        success : function(result) {
            const select = document.getElementById("model");
            select.options.length = 0;

            for (let i = 0; i < result.length; i++) {
                select.options[select.options.length] = new Option(result[i], i.toString());
            }
            }
        }
    )
}

function getAnswer() {
    $.ajax({
            type : "POST",
            url : "answer",
            contentType: "application/json",
            data: JSON.stringify(getInfoTaskAndConditions()),
            success : function(result) {
                document.getElementById("answer").value = result;
            }
        }
    )
}

function save() {
    $.ajax({
            type : "POST",
            url : "save",
            contentType: "application/json",
            data: JSON.stringify(getInfoTaskAndConditions()),
            success : function(result) {
                alert(result);
            }
        }
    )
}

function download() {
    const selectName = document.getElementById("model");
    const nameTask = selectName.options[selectName.selectedIndex].text;

    if(document.getElementById("showData") != null) {
        document.getElementById("showData").remove()
    }

    const eDialogForTable = document.createElement("dialog");
    eDialogForTable.show()
    eDialogForTable.id = "showData"
    document.body.appendChild(eDialogForTable)

    $.ajax({
            type : "GET",
            url : "download/" + nameTask,
            success : function(result) {
                let i;
                const tasks = result;
                const col = [];

                for (i = 0; i < tasks.length; i++) {
                    for (const key in tasks[i]) {
                        if (col.indexOf(key) === -1) {
                            col.push(key);
                        }
                    }
                }

                const table = document.createElement("table");

                let tr = table.insertRow(-1);

                for (i = 0; i < col.length; i++) {
                    const th = document.createElement("th");
                    th.innerHTML = col[i];
                    tr.appendChild(th);
                }

                for (i = 0; i < tasks.length; i++) {
                    tr = table.insertRow(-1);
                    tr.id = tasks[i][col[0]];

                    for (let j = 0; j < col.length; j++) {
                        const tabCell = tr.insertCell(-1);
                        tabCell.className = j.toString()

                        if(0 === j) {
                            tabCell.innerHTML = "<input type=\"button\" value=\"Посчитать\" id=" + tr.id + " onclick='forDownloadGetAnswer(id)'>";
                        } else {
                            tabCell.innerHTML = tasks[i][col[j]];
                        }
                    }
                }

                const divContainer = document.getElementById("showData");
                divContainer.innerHTML = "";
                divContainer.appendChild(table);

                const bClosed = document.createElement("button");
                bClosed.textContent = "Закрыть"
                bClosed.onclick = () => { eDialogForTable.remove() }
                divContainer.appendChild(bClosed)
            }
        }
    )

}

function forDownloadGetAnswer(id) {
    document.getElementById('conditions').textContent = document.getElementById(id).getElementsByClassName("2")[0].innerText
    getAnswer()
}

function exportInFile() {
    let blob = new Blob([JSON.stringify(getInfoTaskAndConditions())], {type: "text/plain"});
    let link = document.createElement("a");
    link.setAttribute("href", URL.createObjectURL(blob));
    link.setAttribute("download", Date.now().toString());
    link.click();
}

function importFile() {
    $("#getFile").change(function(){
        const reader = new FileReader();
        reader.onload = function(ev) {
            const conditionsAndNameTask = JSON.parse(reader.result);

            document.getElementById('conditions').textContent = conditionsAndNameTask.conditions;

            const selectName = document.getElementById("model");

            if(selectName[selectName.selectedIndex].text !== conditionsAndNameTask.taskName) {
                selectItemByValue(selectName, conditionsAndNameTask.taskName)
            }

            getAnswer()
        };

        reader.readAsText($("#getFile")[0].files[0])
    });
}

function getInfoTaskAndConditions() {
    const selectName = document.getElementById("model");
    const nameTask = selectName.options[selectName.selectedIndex].text;
    const conditions = document.getElementById("conditions").value;
    return {"conditions": conditions, "taskName": nameTask};
}

function selectItemByValue(element, value){
    for(let i=0; i < element.options.length; i++) {
        if(element.options[i].text === value) {
            element.selectedIndex = i;
            break;
        }
    }
}
