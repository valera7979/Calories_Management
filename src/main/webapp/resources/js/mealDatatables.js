var ajaxUrl = "ajax/profile/meals/";
var datatableApi;

function updateTable() {
    $.get(ajaxUrl, updateTableByData);
}

function clearFilter() {
    $("#filter")[0].reset();
    $.get(ajaxUrl, updateTableByData);
}

$(function () {
    datatableApi = $("#datatable").DataTable({
        "ajax": {
            "url": ajaxUrl,
            "dataSrc": ""
        },
        "paging": false,
        "info": true,

        "columns": [
            {
                "data": "dateTime",
                "render": function (date, type, row) {
                    if (type === 'display') {
                        return '<span>' + date.replace('T', ' ') + '</span>';
                    }
                    return date;
                }
            },
            {
                "data": "description"
            },
            {
                "data": "calories"
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderEditBtn
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderDeleteBtn
            }
        ],
        "order": [
            [
                0,
                "desc"
            ]
        ],
        "createdRow": function (row, data, dataIndex) {
            if (dataIndex.exceeded) {
                $(row).addClass("exceeded");
            }
        },
        "initComplete": makeEditable
    });

});