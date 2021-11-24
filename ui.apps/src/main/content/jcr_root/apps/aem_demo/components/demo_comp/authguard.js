$("body").hide();
if (JSON.parse(localStorage['firebaseui::rememberedAccounts'])[0].email === 'glpg-web@test.com') {
    $("body").show();
}