Public Class Inscricao
    Private Sub Incricao_Load(sender As Object, e As EventArgs) Handles MyBase.Load
        Me.ControlBox = False
        Me.INSCRICAOTableAdapter.Fill(Me.BDbEdi2021DataSet8.INSCRICAO)
    End Sub

    Private Sub ButtonNovo_Click(sender As Object, e As EventArgs) Handles ButtonNovo.Click
        INSCRICAOBindingSource.AddNew()
    End Sub

    Private Sub ButtonGuardar_Click(sender As Object, e As EventArgs) Handles ButtonGuardar.Click
        INSCRICAOBindingSource.EndEdit()
        INSCRICAOTableAdapter.Update(BDbEdi2021DataSet8)
        MessageBox.Show("Dados guardados com sucesso :)")
    End Sub

    Private Sub ButtonApagar_Click(sender As Object, e As EventArgs) Handles ButtonApagar.Click
        INSCRICAOBindingSource.RemoveCurrent()
    End Sub

    Private Sub ButtonAnterior_Click(sender As Object, e As EventArgs) Handles ButtonAnterior.Click
        Me.Hide()
        Utilizador.Show()
    End Sub

    Private Sub ButtonProximo_Click(sender As Object, e As EventArgs) Handles ButtonProximo.Click
        Me.Hide()
        Participacao.Show()
    End Sub

    Private Sub ButtonFechar_Click(sender As Object, e As EventArgs) Handles ButtonFechar.Click
        Application.Exit()
    End Sub
End Class