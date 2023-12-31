// // editableTable.js
// function toggleEditMode(button) {
//     var row = button.closest('.editable-row');
//     var editButtons = row.querySelectorAll('.edit-btn');
//     var saveButtons = row.querySelectorAll('.save-btn');
//     var editableFields = row.querySelectorAll('.editable');
//
//     editButtons.forEach(function(editButton) {
//         editButton.style.display = 'none';
//     });
//
//     saveButtons.forEach(function(saveButton) {
//         saveButton.style.display = 'inline-block';
//     });
//
//     editableFields.forEach(function(editableField) {
//         var value = editableField.innerText;
//         editableField.innerHTML = '<input type="text" value="' + value + '">';
//     });
// }
//
// function saveRow(button) {
//     var row = button.closest('.editable-row');
//     var editButtons = row.querySelectorAll('.edit-btn');
//     var saveButtons = row.querySelectorAll('.save-btn');
//     var editableInputs = row.querySelectorAll('.editable input');
//
//     editButtons.forEach(function(editButton) {
//         editButton.style.display = 'inline-block';
//     });
//
//     saveButtons.forEach(function(saveButton) {
//         saveButton.style.display = 'none';
//     });
//
//     editableInputs.forEach(function(input) {
//         var value = input.value;
//         input.parentElement.innerHTML = value;
//     });
// }
