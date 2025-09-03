function resetForm() {

    //フォームの要素を取得
    var form = document.getElementById('inputForm');
    
    //フォームの内容をリセット
    var input = form.querySelectorAll('input[type="text"], input[type="date"]');
    for(var i = 0; i < input.length; i++) {
        input[i].value = '';
    }
}


//全選択のチェックボックスを取得
var selectAll = document.getElementById('selectAll');
//各行のチェックボックスを取得
var rowCheck = document.querySelectorAll('.rowCheck');
//削除ボタンを取得
var deleteButton = document.getElementById('deleteButton');
    
//全選択チェックボックスの処理
if(selectAll) {
    //全選択のチェック状態が変わったときに実行
    selectAll.addEventListener('change', function() {

        //各行のチェックボックスを全選択または全解除する
        for(var i = 0; i < rowCheck.length; i++) {
            rowCheck[i].checked = selectAll.checked;
        }

        //削除ボタンの有効と無効を切り替える
        toggleDeleteButton();
    });
    
    //各行のチェックボックスの処理
    for(var j = 0; j < rowCheck.length; j++) {
        //個別のチェック状態が変わったときに実行
        rowCheck[j].addEventListener('change', toggleDeleteButton);
    }
}
    
    
//削除ボタンのon/off切り替え機能
function toggleDeleteButton() {
    var anyChecked = false;
    //1つでもチェックが入っているか確認
    for(var i = 0; i < rowCheck.length; i++) {
        if(rowCheck[i].checked) {
            //1つでもチェックが入っていたらtrue
            anyChecked = true;
            break;
        }
    }
    
    //anyCheckedがtrueなら削除ボタン有効、falseなら無効
    deleteButton.disabled = !anyChecked;
}

//エラーメッセージのpタグから読み取ってアラートを表示する
window.addEventListener("DOMContentLoaded", () => {
    if(window.errorMessages && window.errorMessages.length > 0) {
        let msg = window.errorMessages.join("\n");
        alert(msg);
    }
});