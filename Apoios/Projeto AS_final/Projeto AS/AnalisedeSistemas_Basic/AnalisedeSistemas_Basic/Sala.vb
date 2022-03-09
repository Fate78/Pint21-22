Public Class Sala
    Private Sub Sala_Load(sender As Object, e As EventArgs) Handles MyBase.Load
        Me.ControlBox = False
        Me.SALATableAdapter.Fill(Me.BDbEdi2021DataSet5.SALA)
    End Sub

    Private Sub ButtonNovo_Click(sender As Object, e As EventArgs) Handles ButtonNovo.Click
        SALABindingSource.AddNew()
    End Sub

    Private Sub ButtonGuardar_Click(sender As Object, e As EventArgs) Handles ButtonGuardar.Click
        SALABindingSource.EndEdit()
        SALATableAdapter.Update(BDbEdi2021DataSet5)
        MessageBox.Show("Dados guardados com sucesso :)")
    End Sub

    Private Sub ButtonApagar_Click(sender As Object, e As EventArgs) Handles ButtonApagar.Click
        SALABindingSource.RemoveCurrent()
    End Sub

    Private Sub ButtonAnterior_Click(sender As Object, e As EventArgs) Handles ButtonAnterior.Click
        Me.Hide()
        Requisitante.Show()
    End Sub

    Private Sub ButtonProximo_Click(sender As Object, e As EventArgs) Handles ButtonProximo.Click
        Me.Hide()
        Reuniao.Show()
    End Sub

    Private Sub ButtonFechar_Click(sender As Object, e As EventArgs) Handles ButtonFechar.Click
        Application.Exit()
    End Sub
End Class