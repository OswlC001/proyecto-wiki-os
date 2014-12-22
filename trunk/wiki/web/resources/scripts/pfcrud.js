function handleSubmit(xhr, status, args, dialog) {
    var jqDialog = jQuery('#' + dialog.id);
    if (args.validationFailed) {
        jqDialog.effect('shake', {times: 3}, 100);
    } else {
        dialog.hide();
    }
}


function handleLoginRequest(xhr, status, args) {
    if (args.validationFailed || !args.loggedIn) {
        PF('dlg').jq.effect("shake", {times: 5}, 1000);
    }
    else {
        PF('dlg').hide();
        $('#loginLink').fadeOut();
    }
}
