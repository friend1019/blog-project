// 삭제 기능
const deleteButton = document.getElementById('delete-btn');

if (deleteButton) {
    deleteButton.addEventListener('click', () => {
        const id = document.getElementById('article-id').value;
        fetch(`/api/articles/${id}`, {
            method: 'DELETE'
        })
            .then(() => {
                alert('삭제가 완료되었습니다.');
                location.replace('/articles');
            });
    });
}

// 수정 기능
const modifyButton = document.getElementById('modify-btn');
const saveButton = document.getElementById('save-btn');
const cancelButton = document.getElementById('cancel-btn');

const titleDisplay = document.getElementById('title-display');
const titleEdit = document.getElementById('title-edit');
const contentDisplay = document.getElementById('content-display');
const contentEdit = document.getElementById('content-edit');

if (modifyButton) {
    modifyButton.addEventListener('click', () => {
        // 제목과 내용 편집 가능하도록 변경
        titleDisplay.classList.add('d-none');
        contentDisplay.classList.add('d-none');

        titleEdit.classList.remove('d-none');
        contentEdit.classList.remove('d-none');

        modifyButton.classList.add('d-none');
        deleteButton.classList.add('d-none');

        saveButton.classList.remove('d-none');
        cancelButton.classList.remove('d-none');
    });
}

// 취소 버튼 클릭 시
if (cancelButton) {
    cancelButton.addEventListener('click', () => {
        // 편집 취소하고 원래 상태로 되돌리기
        titleDisplay.classList.remove('d-none');
        contentDisplay.classList.remove('d-none');

        titleEdit.classList.add('d-none');
        contentEdit.classList.add('d-none');

        modifyButton.classList.remove('d-none');
        deleteButton.classList.remove('d-none');

        saveButton.classList.add('d-none');
        cancelButton.classList.add('d-none');

        // 원래 값으로 되돌리기
        titleEdit.value = titleDisplay.textContent;
        contentEdit.value = contentDisplay.textContent;
    });
}

// 저장 버튼 클릭 시
if (saveButton) {
    saveButton.addEventListener('click', () => {
        const id = document.getElementById('article-id').value;

        fetch(`/api/articles/${id}`, {
            method: 'PUT',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: titleEdit.value,  // input의 value 사용
                content: contentEdit.value // textarea의 value 사용
            })
        })
        .then(() => {
            alert('수정이 완료되었습니다.');

            // 화면 업데이트
            titleDisplay.textContent = titleEdit.value;
            contentDisplay.textContent = contentEdit.value;

            // 편집 모드에서 읽기 모드로 변경
            titleDisplay.classList.remove('d-none');
            contentDisplay.classList.remove('d-none');

            titleEdit.classList.add('d-none');
            contentEdit.classList.add('d-none');

            modifyButton.classList.remove('d-none');
            deleteButton.classList.remove('d-none');

            saveButton.classList.add('d-none');
            cancelButton.classList.add('d-none');
        });
    });
}

// 생성 기능
const createButton = document.getElementById('create-btn');

if (createButton) {
    createButton.addEventListener('click', () => {
        const titleElement = document.getElementById('title');
        const contentElement = document.getElementById('content');

        fetch('/api/articles', {
            method: 'POST',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: titleElement.value,  // input의 value 사용
                content: contentElement.value // textarea의 value 사용
            })
        })
        .then(() => {
            alert('등록 완료되었습니다.');
            location.replace('/articles');
        });
    });
}
