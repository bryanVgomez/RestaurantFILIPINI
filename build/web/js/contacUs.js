(function ($) {
  "use strict";

  var select = $('#selectContact');
  $(document).ready(function () {
    //Siempre que salgamos de un campo de texto, se chequeará esta función
    $("#formContactUs input").keyup(function () {
      var form = $(this).parents("#formContactUs");
      var check = checkCampos(form);
      var checkArea = checkTextArea(form);
      if (check) {
        $("#buttonFormContact").prop("disabled", false);
      } else {
        $("#buttonFormContact").prop("disabled", true);
      }
      if (checkArea) {
        $("#buttonFormContact").prop("disabled", false);
      } else {
        $("#buttonFormContact").prop("disabled", true);
      }
        
    });

    $('#buttonFormContact').on('submit', function () {
      var check = true;
      var form = $(this).parents("#formContactUs");
      for (var i = 0; i < input.length; i++) {
        if (validate(input[i]) == false) {
          showValidate(input[i]);
          check = false;
        }
      }

      return check;
    });

    /*$('#formContactUs').('submit', function () {
      this.reset();
    });*/
  });

//Función para comprobar los campos de texto
  function checkCampos(obj) {
    var camposRellenadosInput = true;
    obj.find("input").each(function () {
      var $this = $(this);
      if ($this.val().length <= 2) {
        camposRellenadosInput = false;
        return false;
      }
    });
    if (camposRellenadosInput == false) {
      return false;
    } else {
      return true;
    }
  }

  function validateSelect(obj) {
    var camposRellenadosSelect = true;
    obj.find("#selectContact").each(function () {
      var $this = $(this);
      if ($this.val() == 'default') {
        camposRellenadosSelect = false;
        return false;
      }
    });
    return camposRellenadosSelect;
  }

  function checkTextArea(obj) {
    var camposRellenadosTextArea = true;
    obj.find("textarea").each(function () {
      var $this = $(this);
      if ($this.val().length <= 2) {
        camposRellenadosTextArea = false;
        return false;
      }
    });
    if (camposRellenadosTextArea == false) {
      return false;
    } else {
      return true;
    }
  }

})(jQuery);
