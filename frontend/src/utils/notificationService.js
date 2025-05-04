import swal from "sweetalert2";

export const notifySuccess = async (message) =>
  swal.fire({
    toast: true,
    icon: "success",
    title: message,
    animation: false,
    position: "top-right",
    showConfirmButton: false,
    timer: 3000,
    timerProgressBar: true,
  });

export const notifyWarning = async (message) =>
  swal.fire({
    toast: true,
    icon: "warning",
    title: message,
    animation: false,
    position: "top-right",
    showConfirmButton: false,
    timer: 3000,
    timerProgressBar: true,
  });

export const notifyError = async (message) =>
  swal.fire({
    toast: true,
    icon: "error",
    title: message,
    animation: false,
    position: "top-right",
    showConfirmButton: false,
    timer: 3000,
    timerProgressBar: true,
  });

export const notifyFormValidationError = async () =>
  swal.fire({
    toast: true,
    icon: "error",
    title: "Fill required inputs before submitting!",
    animation: false,
    position: "top-right",
    showConfirmButton: false,
    timer: 3000,
    timerProgressBar: true,
  });

export const confirmAlert = async (message) => {
  return await swal.fire({
    title: `<h5  style=" font-size: 20px !important;">${message}</h5>`,
    showCancelButton: true,
    cancelButtonText:'<div class="text-white">No</div>',
    confirmButtonText: '<div class="text-white">Yes</div>',
    cancelButtonColor:'#C7253E',
    confirmButtonColor: '#407BFF',
    icon: "warning",
  });
};

export const confirmationAlert = async (message, options = {}) => {
    const { yesButton = "Yes", noButton = "No" } = options;

    return await swal.fire({
        title: `<h5 style="font-size: 20px !important;">${message}</h5>`,
        showCancelButton: true,
        confirmButtonText: yesButton,
        cancelButtonText: noButton,
        confirmButtonColor: '#1A3176',
        cancelButtonColor: '#d33',
        icon: "question",
    });
};
